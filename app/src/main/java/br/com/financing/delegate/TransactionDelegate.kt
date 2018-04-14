package br.com.financing.delegate

import br.com.financing.model.Transaction

interface TransactionDelegate {

    fun delegate(transaction: Transaction)
}