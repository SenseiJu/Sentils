package me.senseiju.sentils.storage

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import javax.sql.rowset.CachedRowSet
import javax.sql.rowset.RowSetProvider

class Database(configFile: ConfigFile) {
    private val source: HikariDataSource

    init {
        source = generateDataSource(configFile)
    }

    private fun generateDataSource(configFile: ConfigFile): HikariDataSource {
        return with (HikariConfig()) {
            jdbcUrl =
                "jdbc:mysql://${configFile.getString("host", "localhost")}:${configFile.getInt("port", 3306)}" +
                        "/${configFile.getString("database", "{NO_DATABASE_SPECIFIED}")}" +
                        "?autoReconnect=true&allowMultiQueries=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false"
            username = configFile.getString("username", "root")
            password = configFile.getString("password", "toor")
            connectionTimeout = 8000
            addDataSourceProperty("cachePrepStmts", "true")

            HikariDataSource(this)
        }
    }

    suspend fun asyncQuery(q: String, vararg replacements: Any = emptyArray()): CachedRowSet {
        return withContext(Dispatchers.IO) {
            query(q, *replacements)
        }
    }

    fun query(q: String, vararg replacements: Any = emptyArray()): CachedRowSet {
        source.connection.use { conn ->
            val s = conn.prepareStatement(q)

            var i = 1

            replacements.forEach { replacement -> s.setObject(i++, replacement) }

            val set = s.executeQuery()

            val cachedSet = RowSetProvider.newFactory().createCachedRowSet()
            cachedSet.populate(set)

            return cachedSet
        }
    }

    suspend fun asyncUpdateQuery(q: String, vararg replacements: Any = emptyArray()) {
        withContext(Dispatchers.IO) {
            updateQuery(q, *replacements)
        }
    }

    fun updateQuery(q: String, vararg replacements: Any = emptyArray()) {
        source.connection.use { conn ->
            val s = conn.prepareStatement(q)

            var i = 1

            replacements.forEach { replacement -> s.setObject(i++, replacement) }

            try {
                s.executeUpdate()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun updateBatchQuery(q: String, vararg replacements: Replacements = emptyArray()) {
        source.connection.use { conn ->
            conn.autoCommit = false

            val s = conn.prepareStatement(q)

            var i = 1

            replacements.forEach { set ->
                set.replacements.forEach { replacement ->
                    s.setObject(i++, replacement)
                }

                s.addBatch()

                i = 1
            }

            try {
                s.executeBatch()
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }

            conn.commit()
            conn.autoCommit = true
        }
    }
}

class Replacements(vararg val replacements: Any = emptyArray())