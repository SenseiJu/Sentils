package me.senseiju.sentils.extensions.primitives

import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

val currencyFormat = run {
    with (DecimalFormat("###,###.##")) {
        groupingSize = 3
        isGroupingUsed = true

        return@run this
    }
}

/**
 * Takes the [Long] as time in seconds and converts to HH:MM:SS
 */
fun Long.toTimeFormat(): String {
    val hours = TimeUnit.SECONDS.toHours(this)
    val minute = TimeUnit.SECONDS.toMinutes(this) - hours * 60
    val second = TimeUnit.SECONDS.toSeconds(this) - hours * 3600 - minute * 60

    return "%02d:%02d:%02d".format(hours, minute, second)
}

/**
 * Converts into a string in the general format of currency
 *
 * Note: When setting [prefix] or [suffix], you will need also need to include spaces where required. The
 * final string is created by combining the prefix, formatted value and suffix with no spaces between them
 *
 * @param prefix string to append before the formatted value
 * @param suffix string to append after the formatted value
 *
 * @return the formatted string with prefix and/or suffix if set
 */
fun Long.asCurrencyFormat(prefix: String? = null, suffix: String? = null): String {
    return "${prefix ?: ""}${currencyFormat.format(this)}${suffix ?: ""}"
}