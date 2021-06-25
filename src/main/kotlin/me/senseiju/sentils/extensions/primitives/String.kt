package me.senseiju.sentils.extensions.primitives

import net.md_5.bungee.api.ChatColor
import java.util.regex.Pattern

private val pattern = Pattern.compile("&#[a-fA-F0-9]{6}")

/**
 * Colors a string with either color code symbols ('&') or RGB ('&#FFFFFF')
 */
fun String.color(): String {
    var string = this

    var matcher = pattern.matcher(this)
    while (matcher.find()) {
        val colorString = string.substring(matcher.start(), matcher.end())
        string = string.replace(colorString, ChatColor.of(colorString.substring(1)).toString())
        matcher = pattern.matcher(string)
    }

    return ChatColor.translateAlternateColorCodes('&', string)
}