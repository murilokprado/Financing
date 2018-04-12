package br.com.financing.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.financing.R
import br.com.financing.model.Transaction
import br.com.financing.model.TransactionType
import br.com.financing.ui.adapter.TransactionListAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class TransactionListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transaction: List<Transaction> = transactionExample()

        configList(transaction)
    }

    private fun configList(transaction: List<Transaction>) {
        lista_transacoes_listview.adapter = TransactionListAdapter(transaction, this)
    }

    private fun transactionExample(): List<Transaction> {
        return listOf(Transaction(BigDecimal(20.5), "Comida", TransactionType.EXPENSE),
                Transaction(BigDecimal(100), "Economia", TransactionType.INCOME),
                Transaction(BigDecimal(2000), TransactionType.EXPENSE),
                Transaction(BigDecimal(500), "Prêmio", TransactionType.INCOME),
                Transaction(BigDecimal(500), "CARTÃO DE CRÉDITO", TransactionType.INCOME))
    }
}