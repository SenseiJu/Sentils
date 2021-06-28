package me.senseiju.sentils

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Simple cooldown manager to track the last time of something happening with a constant cooldown.
 * Useful for command cooldowns and similar applications
 *
 * @param cooldown the cooldown provided in seconds
 * @param cleanupPeriod See [CleanupTask.period]
 */
open class CooldownManager<K>(
    private val cooldown: Long,
    cleanupPeriod: Long = TimeUnit.MINUTES.toMillis(5)
) : CleanupTask() {
    private val cooldowns = hashMapOf<K, Long>()

    /**
     * Starts the cooldown for the given key. If a cooldown already exists and this method is called,
     * it will be reset. Use [isOnCooldown] before starting to avoid resetting
     *
     * @param key the key
     */
    fun start(key: K) {
        cooldowns[key] = System.currentTimeMillis()
    }

    /**
     * Checks to see if the provided key is on cooldown
     *
     * @param key the key
     *
     * @return true if on cooldown
     */
    fun isOnCooldown(key: K) = cooldowns[key]?.let { System.currentTimeMillis() - it <= cooldown } == true

    /**
     * Get the time remaining for a key
     *
     * @param key the key
     *
     * @return the time remaining in seconds
     */
    fun timeRemaining(key: K) = (cooldown - (System.currentTimeMillis() - (cooldowns[key] ?: 0))) / 1000

    /**
     * Cleans up the [cooldowns] to remove any unnecessary entries
     */
    override fun cleanup() {
        cooldowns.forEach {
            if (System.currentTimeMillis() - it.value > cooldown) {
                cooldowns.remove(it.key)
            }
        }
    }
}