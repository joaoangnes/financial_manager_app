package com.example.gerenciadorfinanceiro.view

import android.app.Dialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gerenciadorfinanceiro.Category
import com.example.gerenciadorfinanceiro.CategoryData
import com.example.gerenciadorfinanceiro.R
import com.example.gerenciadorfinanceiro.Transaction
import com.example.gerenciadorfinanceiro.TransactionData
import com.example.gerenciadorfinanceiro.Util
import com.example.gerenciadorfinanceiro.helper.SharedPreferencesHelper
import com.example.gerenciadorfinanceiro.helper.SharedPreferencesHelper.Companion.sharedPreferencesCategory
import com.example.gerenciadorfinanceiro.helper.SharedPreferencesHelper.Companion.sharedPreferencesTransaction

// Classe de View Holder para as Transações
class TransactionViewHolder (transactionView: View): ViewHolder(transactionView){
    var txtTransactionDescription: TextView = transactionView.findViewById(R.id.txtTransactionDescription)
    var txtTransactionAmount: TextView = transactionView.findViewById(R.id.txtTransactionAmount)
    var txtTransactionCategory: TextView = transactionView.findViewById(R.id.txtTransactionCategory)
    private var ivEditTransaction: ImageView = transactionView.findViewById(R.id.ivEditTransaction)
    private var ivDeleteTransaction: ImageView = transactionView.findViewById(R.id.ivDeleteTransaction)

    init {
        // Configura o evento de clique no botão de edição
        ivEditTransaction.setOnClickListener {
            val transaction = TransactionData.transactionList.getOrNull(adapterPosition)

            if (transaction != null) {
                val dialog = Dialog(transactionView.context)
                dialog.setContentView(R.layout.dialog_edit_transaction)

                val edtNewTransactionDescription = dialog.findViewById<EditText>(R.id.edtNewTransactionDescription)
                val edtNewTransactionAmount = dialog.findViewById<EditText>(R.id.edtNewTransactionAmount)
                val spnNewCategory = dialog.findViewById<Spinner>(R.id.spnNewCategory)
                val btnSaveTransaction = dialog.findViewById<Button>(R.id.btnSaveTransaction)

                // Preencha o EditText com os valores atuais da transação
                edtNewTransactionDescription.setText(transaction.description)
                edtNewTransactionAmount.setText(transaction.value.toString())

                // Configure o Spinner com as categorias existentes
                val categories = sharedPreferencesCategory.getCategories().map { it.name }
                val adapter = ArrayAdapter(transactionView.context, android.R.layout.simple_spinner_item, categories)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spnNewCategory.adapter = adapter

                // Defina a categoria atual como selecionada no Spinner
                val currentCategoryIndex = categories.indexOf(transaction.category.name)
                if (currentCategoryIndex != -1) {
                    spnNewCategory.setSelection(currentCategoryIndex)
                }

                btnSaveTransaction.setOnClickListener {
                    val newTransactionDescription = edtNewTransactionDescription.text.toString()
                    val newTransactionAmount = edtNewTransactionAmount.text.toString().toDoubleOrNull()
                    val newCategoryName = spnNewCategory.selectedItem.toString()

                    if (newTransactionDescription.isNotBlank() && newTransactionAmount != null && newCategoryName.isNotBlank()) {
                        val updatedCategory = sharedPreferencesCategory.getCategories().find { it.name == newCategoryName }
                        if (updatedCategory != null) {
                            val updatedTransaction = Transaction(transaction.id, newTransactionDescription, newTransactionAmount, updatedCategory)
                            sharedPreferencesTransaction.editTransaction(transaction.id, updatedTransaction)
                            dialog.dismiss()
                            Util.exibirToast(transactionView.context, "Transação editada: ${transaction.description} -> $newTransactionDescription")
                        } else {
                            Util.exibirToast(transactionView.context, "Categoria não encontrada.")
                        }
                    } else {
                        Util.exibirToast(transactionView.context, "Preencha todos os campos corretamente.")
                    }
                }

                // Mostre o diálogo
                dialog.show()
            }
        }

        // Configura o evento de clique no botão de exclusão
        ivDeleteTransaction.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val transactionToDelete = TransactionData.transactionList[position]

                // Certifique-se de que a transação a ser excluída seja a mesma na lista
                if (transactionToDelete != null) {
                    sharedPreferencesTransaction.deleteTransaction(transactionToDelete)
                    Util.exibirToast(transactionView.context, "Transação excluída!")
                } else {
                    // Transação não encontrada na lista
                    Util.exibirToast(transactionView.context, "Erro: Transação não encontrada na lista!")
                }
            }
        }

    }

}