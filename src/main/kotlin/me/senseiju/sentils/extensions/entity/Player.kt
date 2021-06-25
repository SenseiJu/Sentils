package me.senseiju.sentils.extensions.entity

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import kotlin.IllegalArgumentException

/**
 * Quicker way to play a sound to a player. Automatically sets the location to their current position
 * and sets the volume and pitch to max by default
 */
fun Player.playSound(sound: Sound, volume: Float = 1.0f, pitch: Float = 1.0f) {
    playSound(location, sound, volume, pitch)
}

/**
 * Adds an item to the player's inventory. This will also drop any remaining items that did not fit into
 * the player's inventory onto the ground
 */
@Throws(IllegalArgumentException::class)
fun PlayerInventory.addItemOrDropNaturally(item: ItemStack, location: Location? = this.location) {
    if (location == null || location.world == null) {
        throw IllegalArgumentException(
            "Cannot add item to inventory as the fallback drop location is null or the world is null."
        )
    }

    val remaining = this.addItem(item)
    if (remaining.isNotEmpty()) {
        remaining.values.forEach { location.world!!.dropItemNaturally(location, it) }
    }
}