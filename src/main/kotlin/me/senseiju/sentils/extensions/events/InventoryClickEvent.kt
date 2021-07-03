package me.senseiju.sentils.extensions.events

import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Gets [InventoryClickEvent.getWhoClicked] as a Player
 *
 * Note: This could change if a new [HumanEntity] is added to Bukkit
 */
val InventoryClickEvent.player: Player
    get() = whoClicked as Player