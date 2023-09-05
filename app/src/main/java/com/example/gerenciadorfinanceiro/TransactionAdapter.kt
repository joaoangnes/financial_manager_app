package com.example.gerenciadorfinanceiro

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadorfinanceiro.databinding.ItemTransactionBinding
import com.example.gerenciadorfinanceiro.view.CategoryViewHolder
import com.example.gerenciadorfinanceiro.view.TransactionViewHolder

// Classe que funciona como um adaptador para a RecyclerView
class TransactionAdapter(private val context: Context) : RecyclerView.Adapter<TransactionViewHolder>() {

    // Método chamado quando a RecyclerView precisa de um novo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        // Infla o layout do item de transação a partir do XML
        var transactionView = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(transactionView) // Retorna um ViewHolder para o item
    }

    // Método chamado para preencher os dados em um ViewHolder
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        // Obtém o objeto de transação com base na posição na lista de transações
        var currentItem = TransactionData.transactionList[position]
        // Define o texto nos elementos de interface do usuário do ViewHolder
        holder.txtTransactionDescription.text = "Descrição: ${currentItem.description}"
        holder.txtTransactionAmount.text = "Valor: ${currentItem.value.toString()}"
        holder.txtTransactionCategory.text = "Categoria: ${currentItem.category.name}"
    }

    // Método chamado para obter o número total de itens na lista de transações
    override fun getItemCount(): Int {
        return TransactionData.getListSize()
    }
}
