package br.com.financing.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.financing.R
import br.com.financing.extension.formatCurrencyBr
import br.com.financing.model.Abstract
import br.com.financing.model.Transaction
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class AbstractView(private val context: Context,
                   private val view: View,
                   transactionList: List<Transaction>) {

    private val abstract: Abstract = Abstract(transactionList)
    private val colorExpense = ContextCompat.getColor(context, R.color.despesa)
    private val colorIncome = ContextCompat.getColor(context, R.color.receita)

    private fun addIncome() {
        var totalIncome = abstract.income

        with(view.resumo_card_receita) {
            setTextColor(colorIncome)
            text = totalIncome.formatCurrencyBr()
        }
    }

    private fun addExpense() {
        var totalExpense = abstract.expense
        with(view.resumo_card_despesa) {
            setTextColor(colorExpense)
            text = totalExpense.formatCurrencyBr()
        }
    }

    private fun addTotal() {
        var total = abstract.total

        val color: Int = changeColor(total)

        with(view.resumo_card_total) {
            setTextColor(color)
            text = total.formatCurrencyBr()
        }
    }

    private fun changeColor(total: BigDecimal): Int {
        return if (total >= BigDecimal.ZERO) {
            colorIncome
        } else {
            colorExpense
        }
    }

    fun update() {
        addIncome()
        addExpense()
        addTotal()
    }
}