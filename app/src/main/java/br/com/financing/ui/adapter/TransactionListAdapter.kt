package br.com.financing.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.financing.R
import br.com.financing.extension.formatCurrencyBr
import br.com.financing.extension.formatDatePtBr
import br.com.financing.extension.formatSizeString
import br.com.financing.model.Transaction
import br.com.financing.model.TransactionType
import kotlinx.android.synthetic.main.transacao_item.view.*

class TransactionListAdapter(private val transaction: List<Transaction>,
                             private val context: Context) : BaseAdapter() {

    private val LIMIT_OF_CATEGORY = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transaction = transaction[position]

        addValue(view, transaction)
        addIcon(transaction, view)
        addCategory(view, transaction)
        addDate(view, transaction)

        return view
    }

    private fun addDate(view: View, transaction: Transaction) {
        view.transacao_data.text = transaction.date.formatDatePtBr()
    }

    private fun addCategory(view: View, transaction: Transaction) {
        view.transacao_categoria.text = transaction.category.formatSizeString(LIMIT_OF_CATEGORY)
    }

    private fun addValue(view: View, transaction: Transaction) {
        view.transacao_valor.text = transaction.value.formatCurrencyBr()
        addColor(transaction, view)
    }

    private fun addIcon(transaction: Transaction, view: View) {
        val icon: Int = selectIcon(transaction)
        view.transacao_icone.setBackgroundResource(icon)
    }

    private fun selectIcon(transaction: Transaction): Int {
        return if (transaction.transactionType == TransactionType.INCOME) {
            R.drawable.icone_transacao_item_receita
        } else {
            R.drawable.icone_transacao_item_despesa
        }
    }

    private fun addColor(transaction: Transaction, view: View) {
        val color: Int = selectColor(transaction)
        view.transacao_valor.setTextColor(color)
    }

    private fun selectColor(transaction: Transaction): Int {
        return if (transaction.transactionType == TransactionType.INCOME) {
            ContextCompat.getColor(context, R.color.receita)
        } else {
            ContextCompat.getColor(context, R.color.despesa)
        }
    }

    override fun getItem(position: Int): Transaction {
        return transaction[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transaction.size
    }
}