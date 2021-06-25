package me.senseiju.sentils.runnables

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * Similar to [CountdownRunnable] but add a [UUID] property for players
 *
 * @property uuid the player's uuid
 */
abstract class PlayerCountdownRunnable : CountdownRunnable() {
    abstract val uuid: UUID

    /**
     * Get's a [Player] based off the [uuid]
     *
     * @return [Bukkit.getPlayer]
     */
    fun getPlayer(): Player? = Bukkit.getPlayer(uuid)
}