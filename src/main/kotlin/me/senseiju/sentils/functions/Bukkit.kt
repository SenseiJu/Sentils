package me.senseiju.sentils.functions

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * Gets a [Player] and checks if they are online
 *
 * @param uuid the uuid of the player
 *
 * @return [Player] if online else null
 */
fun getOnlinePlayer(uuid: UUID): Player? {
    return Bukkit.getPlayer(uuid)?.let {
        return if (it.isOnline) {
            it
        } else {
            null
        }
    }
}

/**
 * Gets a [Player] by their exact username if they are online
 *
 * @param name the exact username
 *
 * @return [Player] if online else null
 */
fun getOnlinePlayer(name: String): Player? {
    return Bukkit.getPlayerExact(name)?.let {
        return if (it.isOnline) {
            it
        } else {
            null
        }
    }
}