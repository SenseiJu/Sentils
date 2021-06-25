package me.senseiju.sentils

import me.senseiju.sentils.runnables.newRunnable
import org.bukkit.plugin.java.JavaPlugin
import java.net.URL

private const val SPIGOT_API_URL = "https://api.spigotmc.org/legacy/update.php?resource="

fun getSpigotResourceVersion(plugin: JavaPlugin, resourceId: Int, callback: (String) -> Unit) {
    newRunnable {
        callback(URL("$SPIGOT_API_URL$resourceId").readText())

    }.runTaskAsynchronously(plugin)
}