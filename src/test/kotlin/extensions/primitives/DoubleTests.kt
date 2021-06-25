package extensions.primitives

import me.senseiju.sentils.extensions.primitives.asCurrencyFormat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DoubleTests {

    @Test
    fun currencyFormatting() {
        val data = 1234567.89

        Assertions.assertEquals("$1,234,567.89", data.asCurrencyFormat("$"))
        Assertions.assertEquals("1,234,567.89 Coins", data.asCurrencyFormat(suffix = " Coins"))
    }
}