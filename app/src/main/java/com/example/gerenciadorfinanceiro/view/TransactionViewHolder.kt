package com.example.gerenciadorfinanceiro.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gerenciadorfinanceiro.R

// Classe de View Holder para as Transações
class TransactionViewHolder (transactionView: View): ViewHolder(transactionView){
    var txtTransactionDescription: TextView = transactionView.findViewById(R.id.txtTransactionDescription)
    var txtTransactionAmount: TextView = transactionView.findViewById(R.id.txtTransactionAmount)
    var txtTransactionCategory: TextView = transactionView.findViewById(R.id.txtTransactionCategory)
}