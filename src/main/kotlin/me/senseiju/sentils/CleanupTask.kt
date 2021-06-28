package me.senseiju.sentils

import kotlin.concurrent.thread

/**
 * Starts a task that will automatically perform cleanup operations based on a period
 *
 * @property period The time between cleanups in milliseconds defaulting to 5 minutes.
 *                  Use [TimeUnit] to easily describe your cleanup periods. If you enter -1 or less, the cleanup task
 *                  will not run, useful for optional cleanups
 */
abstract class CleanupTask(
    private val period: Long = -1
) {
    init {
        if (period >= 0) {
            startCleanup()
        }
    }

    /**
     * Starts the cleanup task
     */
    private fun startCleanup() {
        thread {
            while (true) {
                Thread.sleep(period)

                cleanup()
            }
        }
    }

    /**
     * Describes how cleaning up should be done. This will be executed based on the period provided
     */
    protected abstract fun cleanup()
}