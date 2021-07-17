import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.senseiju.sentils.extensions.asString
import me.senseiju.sentils.serializers.LocationSerializer
import me.senseiju.sentils.serializers.UUIDSerializer
import org.bukkit.Location
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.*

class SerializerTests {

    companion object {
        private lateinit var server: ServerMock
        private lateinit var data: TestData

        @JvmStatic
        @BeforeAll
        fun setup() {
            server = MockBukkit.getOrCreateMock()

            data = TestData(
                uuid = UUID.randomUUID(),
                location = Location(server.addSimpleWorld("Test"), 1.134, -124.23, 10000.0, 244.22f, 23.34f)
            )
        }

        @JvmStatic
        @AfterAll
        fun cleanup() {
            MockBukkit.unmock()
        }
    }

    @Test
    fun testSerializers() {
        val uuid = UUID.randomUUID()
        val location = Location(server.addSimpleWorld("Test"), 1.134, -124.23, 10000.0, 244.22f, 23.34f)

        val testJson = """
            {
            	"uuid": "$uuid",
            	"location": "${location.asString()}"
            }
        """.trimIndent()

        val data = Json.decodeFromString<TestData>(testJson)

        Assertions.assertEquals(uuid, data.uuid)
        Assertions.assertEquals(location.asString(), data.location.asString())
    }
}

@Serializable
private data class TestData(
    @Serializable(UUIDSerializer::class) val uuid: UUID,
    @Serializable(LocationSerializer::class) val location: Location
)