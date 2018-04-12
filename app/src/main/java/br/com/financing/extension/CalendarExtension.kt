package br.com.financing.extension

import java.text.SimpleDateFormat
import java.util.Calendar

fun Calendar.formatDatePtBr() : String {
    val formatPtBr = "dd/MM/yyyy"
    return SimpleDateFormat(formatPtBr).format(this.time)
}