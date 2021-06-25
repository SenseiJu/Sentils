package data_storage

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.MockPlugin
import me.senseiju.sentils.storage.BukkitConfigFile
import org.bukkit.Location
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class BukkitConfigFileTests {

    companion object {
        private lateinit var plugin: MockPlugin

        @JvmStatic
        @BeforeAll
        fun setup() {
            MockBukkit.getOrCreateMock()

            plugin = MockBukkit.createMockPlugin()
        }

        @JvmStatic
        @AfterAll
        fun cleanup() {
            MockBukkit.unmock()
        }
    }

    @Test
    fun testConfigFile() {
        val file = BukkitConfigFile(plugin, "test.yml", true)
        val location = Location(null, 1.0, 1.2, -5.0, 34F, 350F)

        file.setLocation("location", location)

        Assertions.assertEquals(file.getLocation("location"), location)
    }
}