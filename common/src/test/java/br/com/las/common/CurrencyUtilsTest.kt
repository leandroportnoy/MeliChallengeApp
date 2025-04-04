package br.com.las.common

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyUtilsTest {

    @Test
    fun `should format value as brazilian currency`() {
        val value = 1234.56
        val formatted = value.toBrazilianCurrency()

        assertEquals("R$ 1.234,56", formatted)
    }

    @Test
    fun `should format zero value correctly`() {
        val formatted = 0.0.toBrazilianCurrency()
        assertEquals("R$ 0,00", formatted)
    }

    @Test
    fun `should format negative value correctly`() {
        val formatted = (-45.67).toBrazilianCurrency()
        assertEquals("-R$ 45,67", formatted)
    }
}
