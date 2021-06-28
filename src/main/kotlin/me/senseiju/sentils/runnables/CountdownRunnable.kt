package me.senseiju.sentils.runnables

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * [BukkitRunnable] with a countdown to execute something later. The timer will tick once every second.
 *
 * @property timeToComplete should be set in seconds
 * @property finished true if the countdown is over
 */
abstract class CountdownRunnable : BukkitRunnable() {
    abstract var timeToComplete: Long

    var finished = false
        private set

    override fun run() {
        if (timeToComplete <= 1) {
            finished = true

            onComplete()
            cancel()

            return
        }

        timeToComplete--

        executeEverySecond()
    }

    /**
     * Starts the countdown
     *
     * @param plugin the plugin
     */
    fun start(plugin: JavaPlugin) {
        runTaskTimer(plugin, 0L, 20L)
    }

    /**
     * Executes once the countdown is finished
     */
    protected abstract fun onComplete()

    /**
     * Executes every second
     */
    protected open fun executeEverySecond() {}
}