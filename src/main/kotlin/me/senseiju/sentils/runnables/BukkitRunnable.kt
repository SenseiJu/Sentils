package me.senseiju.sentils.runnables

import org.bukkit.scheduler.BukkitRunnable

/**
 * Creates a new [BukkitRunnable]
 */
fun newRunnable(block: () -> Unit): BukkitRunnable {
    return object : BukkitRunnable() {
        override fun run() {
            block.invoke()
        }
    }
}