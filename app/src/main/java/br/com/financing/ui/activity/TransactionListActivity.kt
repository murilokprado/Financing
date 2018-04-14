package br.com.financing.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import br.com.financing.R
import br.com.financing.delegate.TransactionDelegate
import br.com.financing.model.Transaction
import br.com.financing.model.TransactionType
import br.com.financing.ui.AbstractView
import br.com.financing.ui.adapter.TransactionListAdapter
import br.com.financing.ui.dialog.AddTransactionDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class TransactionListActivity : AppCompatActivity() {

    private val transactionList: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configAbstract()

        configList()

        lista_transacoes_adiciona_receita
                .setOnClickListener {
                    AddTransactionDialog(window.decorView as ViewGroup, this)
                            .configDialog(TransactionType.INCOME, object : TransactionDelegate {
                                override fun delegate(transaction: Transaction) {
                                    updateTransaction(transaction)
                                    lista_transacoes_adiciona_menu.close(true)
                                }
                            })
                }

        lista_transacoes_adiciona_despesa
                .setOnClickListener {
                    AddTransactionDialog(window.decorView as ViewGroup, this)
                            .configDialog(TransactionType.EXPENSE, object : TransactionDelegate {
                                override fun delegate(transaction: Transaction) {
                                    updateTransaction(transaction)
                                    lista_transacoes_adiciona_menu.close(true)
                                }
                            })
                }
    }

    private fun updateTransaction(transaction: Transaction) {
        transactionList.add(transaction)
        configList()
        configAbstract()
    }

    private fun configAbstract() {
        val decorView: View = window.decorView
        AbstractView(this, decorView, transactionList).update()
    }

    private fun configList() {
        lista_transacoes_listview.adapter = TransactionListAdapter(transactionList, this)
    }
}