package me.senseiju.sentils.extensions

import me.senseiju.sentils.extensions.primitives.color

/**
 * Use's [String.color] to apply color to each string
 */
fun Collection<String>.color() = map(String::color)