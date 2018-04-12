package br.com.financing.model

import java.math.BigDecimal
import java.util.Calendar

class Transaction(val value: BigDecimal,
                  val category: String,
                  val transactionType: TransactionType,
                  val date: Calendar = Calendar.getInstance()) {

    constructor(value: BigDecimal, transactionType: TransactionType) : this(value, "Indefinida", transactionType)

    constructor(value: BigDecimal, transactionType: TransactionType, data: Calendar) : this(value, "Indefinida", transactionType, data)
}