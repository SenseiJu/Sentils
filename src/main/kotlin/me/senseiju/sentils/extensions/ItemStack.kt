package me.senseiju.sentils.extensions

import org.bukkit.inventory.ItemStack

/**
 * Checks if the item is null or air
 *
 * @return true if null or air
 */
fun ItemStack?.isNullOrAir(): Boolean {
    return this == null || type.isAir
}

/**
 * Filters out all [ItemStack] from the array that are null or air
 */
inline fun Array<ItemStack>.forEachNotNullOrAir(action: (ItemStack) -> Unit) {
    forEach { itemStack ->
        if (!itemStack.isNullOrAir()) {
            action(itemStack)
        }
    }
}