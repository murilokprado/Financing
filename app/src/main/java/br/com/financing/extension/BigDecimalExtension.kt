package br.com.financing.extension

import java.math.BigDecimal

fun BigDecimal.formatCurrencyBr(): String {
    val formatCurrencyBr = java.text.DecimalFormat.getCurrencyInstance(java.util.Locale("pt", "br"))
    return formatCurrencyBr.format(this).replace("R$", "R$ ")
}