package me.senseiju.sentils.extensions.primitives

fun Int.toTimeFormat(): String {
    return toLong().toTimeFormat()
}