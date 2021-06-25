package extensions.primitives

import me.senseiju.sentils.extensions.primitives.asCurrencyFormat
import me.senseiju.sentils.extensions.primitives.toTimeFormat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LongTests {

    @Test
    fun currencyFormatting() {
        val data = 1234567891011

        Assertions.assertEquals("$1,234,567,891,011", data.asCurrencyFormat("$"))
        Assertions.assertEquals("1,234,567,891,011 Coins", data.asCurrencyFormat(suffix = " Coins"))
    }

    @Test
    fun timeFormatting() {
        val timeInSeconds = 43876423

        Assertions.assertEquals("12187:53:43", timeInSeconds.toTimeFormat())
    }
}