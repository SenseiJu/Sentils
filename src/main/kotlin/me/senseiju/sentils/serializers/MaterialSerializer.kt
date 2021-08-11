package me.senseiju.sentils.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bukkit.Material

object MaterialSerializer : KSerializer<Material> {

    override val descriptor = PrimitiveSerialDescriptor("Material", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Material) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Material {
        return Material.matchMaterial(decoder.decodeString()) ?: Material.BEDROCK
    }
}