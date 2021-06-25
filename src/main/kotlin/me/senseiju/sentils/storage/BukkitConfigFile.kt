package me.senseiju.sentils.storage

import me.senseiju.sentils.extensions.asString
import me.senseiju.sentils.extensions.locationFromString
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class BukkitConfigFile(
    private val plugin: JavaPlugin,
    private val path: String,
    private val hasDefault: Boolean = false
) {
    private lateinit var file: File
    private lateinit var config: YamlConfiguration

    init {
        reload()
    }

    /**
     * Reloads the currently stored configuration to the latest data in the file. If you have made changes,
     * make sure that you [save] before reloading.
     */
    fun reload() {
        file = File("${plugin.dataFolder}/${path}")

        if (!file.exists()) {
            plugin.dataFolder.mkdirs()
            if (hasDefault) {
                plugin.saveResource(path, false)
            } else {
                file.createNewFile()
            }
        }

        config = YamlConfiguration.loadConfiguration(file)
    }

    /**
     * Save any changes make to the config
     */
    fun save() = config.save(file)

    fun set(path: String, value: Any) { config.set(path, value) }

    /**
     * General getter for config
     */
    fun get(path: String, default: Any?) = config.get(path, default)

    fun getString(path: String, default: String) = config.getString(path, default)!!

    fun getInt(path: String, default: Int) = config.getInt(path, default)

    fun getLong(path: String, default: Long) = config.getLong(path, default)

    fun getBoolean(path: String, default: Boolean) = config.getBoolean(path, default)

    fun getDouble(path: String, default: Double) = config.getDouble(path, default)

    fun getStringList(path: String): MutableList<String> = config.getStringList(path)

    fun getIntList(path: String): MutableList<Int> = config.getIntegerList(path)

    fun getLongList(path: String): MutableList<Long> = config.getLongList(path)

    fun getDoubleList(path: String): MutableList<Double> = config.getDoubleList(path)

    fun getBooleanList(path: String): MutableList<Boolean> = config.getBooleanList(path)

    /**
     * Sets the value of the path to a [Location] in the format [Location.asString]
     */
    fun setLocation(path: String, location: Location) {
        set(path, location.asString())
    }

    /**
     * Gets the [Location] from the path. Must be in the format returned by [Location.asString]
     */
    fun getLocation(path: String) = config.getString(path)?.let { locationFromString(it) }
}