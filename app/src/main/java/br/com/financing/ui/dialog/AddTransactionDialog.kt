package br.com.financing.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.financing.R
import br.com.financing.delegate.TransactionDelegate
import br.com.financing.extension.formatCalendar
import br.com.financing.extension.formatDatePtBr
import br.com.financing.model.Transaction
import br.com.financing.model.TransactionType
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AddTransactionDialog(private val viewGroup: ViewGroup,
                           private val context: Context) {

    private val viewForDialog = createLayout()

    fun configDialog(transactionType: TransactionType, transactionDelegate: TransactionDelegate) {
        configFieldDate()
        configFieldCategory(transactionType)
        configForm(transactionType, transactionDelegate)
    }

    private fun configForm(transactionType: TransactionType, transactionDelegate: TransactionDelegate) {
        val title = if(transactionType == TransactionType.INCOME) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }

        AlertDialog.Builder(context)
                .setTitle(title)
                .setView(viewForDialog)
                .setPositiveButton("Adicionar",
                        { _, _ ->
                            val valueText = viewForDialog.form_transacao_valor.text.toString()
                            val dateText = viewForDialog.form_transacao_data.text.toString().formatCalendar()
                            val categoryText = viewForDialog.form_transacao_categoria.selectedItem.toString()

                            val value = convertFieldValue(valueText)

                            val transaction = Transaction(value, categoryText, transactionType, dateText)

                            transactionDelegate.delegate(transaction)

                        })
                .setNegativeButton("Cancelar", null)
                .show()
    }

    private fun convertFieldValue(valueText: String): BigDecimal {
        return try {
            BigDecimal(valueText)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversão de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configFieldCategory(transactionType: TransactionType) {
        val category = if(transactionType == TransactionType.INCOME) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }

        val adapter = ArrayAdapter
                .createFromResource(context,
                        category,
                        android.R.layout.simple_spinner_dropdown_item)

        viewForDialog.form_transacao_categoria.adapter = adapter
    }

    private fun configFieldDate() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        viewForDialog.form_transacao_data.setText(today.formatDatePtBr())
        viewForDialog.form_transacao_data
                .setOnClickListener {
                    DatePickerDialog(context,
                            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                                val selectDate = Calendar.getInstance()
                                selectDate.set(year, month, dayOfMonth)
                                viewForDialog.form_transacao_data.setText(selectDate.formatDatePtBr())
                            },
                            year, month, day)
                            .show()
                }
    }

    private fun createLayout(): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.form_transacao, viewGroup, false)
    }
}