package me.senseiju.sentils.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.senseiju.sentils.extensions.asString
import me.senseiju.sentils.extensions.locationFromString
import org.bukkit.Location

object LocationSerializer : KSerializer<Location> {

    override val descriptor = PrimitiveSerialDescriptor("Location", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Location {
        return locationFromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Location) {
        encoder.encodeString(value.asString())
    }

}