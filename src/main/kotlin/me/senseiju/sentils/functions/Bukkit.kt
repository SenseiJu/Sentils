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