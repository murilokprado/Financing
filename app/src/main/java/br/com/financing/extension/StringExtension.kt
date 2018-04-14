package br.com.financing.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

fun String.formatSizeString(size : Int) : String {
    if(this.length > size) {
        val firstCaracter = 0
        return "${this.substring(firstCaracter, size)}..."
    }

    return this
}

fun String.formatCalendar(): Calendar {
    val formatBr = SimpleDateFormat("dd/MM/yyyy")
    val dateFormat: Date = formatBr.parse(this)
    val date = Calendar.getInstance()
    date.time = dateFormat
    return date
}
