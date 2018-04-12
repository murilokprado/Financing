package br.com.financing.extension

fun String.formatSizeString(size : Int) : String {
    if(this.length > size) {
        val firstCaracter = 0
        return "${this.substring(firstCaracter, size)}..."
    }

    return this
}