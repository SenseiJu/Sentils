package me.senseiju.sentils.runnables

import org.bukkit.scheduler.BukkitRunnable

/**
 * Creates a new [BukkitRunnable]
 */
fun newRunnable(toRun: () -> Unit): BukkitRunnable {
    return object : BukkitRunnable() {
        override fun run() {
            toRun()
        }
    }
}