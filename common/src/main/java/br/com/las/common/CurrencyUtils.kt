package br.com.las.common

import java.text.NumberFormat
import java.util.Locale

fun Double.toBrazilianCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this)
}