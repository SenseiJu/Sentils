package me.senseiju.sentils.extensions

import me.senseiju.sentils.Set
import me.senseiju.sentils.extensions.primitives.color
import me.senseiju.sentils.storage.ConfigFile
import org.bukkit.command.CommandSender

object MessageProvider {
    lateinit var messages: ConfigFile
}

fun CommandSender.message(s: String) {
    this.sendMessage(s.color())
}

fun CommandSender.message(list: List<String>) {
    list.forEach { this.message(it) }
}

fun CommandSender.sendConfigMessage(
    messageName: String,
    vararg placeholders: Set = emptyArray()
) {
    this.sendConfigMessage(messageName, prefix = true, placeholders = placeholders)
}

fun CommandSender.sendConfigMessage(
    messageName: String,
    prefix: Boolean = true,
    vararg placeholders: Set = emptyArray()
) {
    val messages = MessageProvider.messages

    if (!messages.isSet(messageName)) {
        this.message("&c&lERROR: &cNo message found at path '$messageName'")
    } else if (messages.isString(messageName)) {
        val toSend = applyPlaceholdersAndPrefix(
            messages.getString(messageName, "Undefined message for '$messageName'"),
            prefix,
            *placeholders
        )

        this.message(toSend)
    } else {
        val toSend = messages.getStringList(messageName).map {
            applyPlaceholdersAndPrefix(it, prefix, *placeholders)
        }

        this.message(toSend)
    }
}

private fun applyPlaceholdersAndPrefix(line: String, prefix: Boolean, vararg placeholders: Set): String {
    var toSend = line

    placeholders.forEach { placeholder ->
        toSend = line.replace(placeholder.any1.toString(), placeholder.any2.toString())
    }

    if (prefix) {
        toSend = "${MessageProvider.messages.getString("PREFIX", "&c&lNO PREFIX SET »")} $line"
    }

    return toSend
}