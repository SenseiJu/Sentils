package me.senseiju.sentils.extensions.primitives

/**
 * See [Long.asCurrencyFormat] for documentation
 */
fun Double.asCurrencyFormat(prefix: String? = null, suffix: String? = null): String {
    return "${prefix ?: ""}${currencyFormat.format(this)}${suffix ?: ""}"
}