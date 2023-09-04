package com.example.gerenciadorfinanceiro

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper.Companion.sharedPreferencesTransaction
import com.example.gerenciadorfinanceiro.databinding.ItemTransactionBinding

class TransactionAdapter(private val transactionList: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
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
