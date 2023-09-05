package com.example.gerenciadorfinanceiro

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper.Companion.sharedPreferencesTransaction
import com.example.gerenciadorfinanceiro.databinding.ItemTransactionBinding

class TransactionAdapter(private val context: Context) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //var transactionView = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        // return TransactionViewHolder(transactionView)
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = TransactionData.transactionList[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return TransactionData.getListSize()
    }

    inner class ViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        /*init {
            // Configura o evento de clique no botão de exclusão
            binding.btnDeleteTransaction.setOnClickListener {
                // Obter a posição da transação a ser excluída
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val trasaction = transactionList[position]
                    sharedPreferencesTransaction.deleteTransaction(trasaction)

                }
            }
        }*/

        fun bind(transaction: Transaction) {
            binding.txtTransactionDescription.text = "Descrição: ${transaction.description}"
            binding.txtTransactionAmount.text = "Valor: ${transaction.value}"
            binding.txtTransactionCategory.text = "Categoria: ${transaction.category.name}"
        }
    }
}
