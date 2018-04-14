package br.com.financing.model

import java.math.BigDecimal

class Abstract(private val transactionList: List<Transaction>) {

    val income get() = sum(TransactionType.INCOME)

    val expense get() = sum(TransactionType.EXPENSE)

    val total get() = income.subtract(expense)

    private fun sum(transactionType: TransactionType) : BigDecimal {
        return BigDecimal(transactionList
                .filter{it.transactionType == transactionType}
                .sumByDouble{it.value.toDouble()})
    }
}