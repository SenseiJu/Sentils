package me.senseiju.sentils

/**
 * Simple cooldown manager to track the last time of something happening with a constant cooldown.
 * Useful for command cooldowns and similar applications
 *
 * @param cooldown the cooldown provided in seconds
 */
open class CooldownManager<K>(private val cooldown: Long) {
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
    fun isOnCooldown(key: K): Boolean {
        return System.currentTimeMillis() - (cooldowns[key] ?: return false) <= cooldown
    }

    /**
     * Get the time remaining for a key
     *
     * @param key the key
     *
     * @return the time remaining in seconds
     */
    fun timeRemaining(key: K): Long {
        return (cooldown - (System.currentTimeMillis() - cooldowns.getOrDefault(key, 0))) / 1000
    }
}