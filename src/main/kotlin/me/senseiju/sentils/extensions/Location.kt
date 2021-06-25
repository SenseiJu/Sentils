package me.senseiju.sentils.extensions

import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * Serializes a [Location] to a string
 */
fun Location.asString(): String {
    return "${world?.name ?: "null"}:$x:$y:$z:$yaw:$pitch"
}

/**
 * Turns a [Location.asString] string into a [Location] object
 */
fun locationFromString(string: String): Location {
    with (string.split(":")) {
        return Location(
            Bukkit.getWorld(this[0]),
            this[1].toDouble(),
            this[2].toDouble(),
            this[3].toDouble(),
            this[4].toFloat(),
            this[5].toFloat()
        )
    }
}