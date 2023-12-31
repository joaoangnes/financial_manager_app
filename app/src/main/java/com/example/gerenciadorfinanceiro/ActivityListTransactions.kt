package com.example.gerenciadorfinanceiro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gerenciadorfinanceiro.helper.SharedPreferencesHelper
import com.example.gerenciadorfinanceiro.helper.SharedPreferencesHelper.Companion.sharedPreferencesTransaction
import com.example.gerenciadorfinanceiro.databinding.ActivityListTransactionsBinding

class ActivityListTransactions : AppCompatActivity() {
    private lateinit var binding: ActivityListTransactionsBinding
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o SharedPreferences para armazenar as categorias
        SharedPreferencesHelper.initialize(this)

        // Configurar a RecyclerView
        setupRecyclerView()

        // Configurar o botão "Adicionar Transação" para redirecionar o usuário
        binding.btnGoTransactionListActivity.setOnClickListener {
            startActivity(Intent(this, ActivityAddTransaction::class.java))
        }

        // Configura o botão "Atualizar Lista" para carregar as transações
        binding.btnListRefresh.setOnClickListener {
            loadTransactions()
        }

        // Carregar a lista de transações da SharedPreferences
        loadTransactions()
    }

    private fun setupRecyclerView() {
        transactionAdapter = TransactionAdapter(this)
        binding.rvTransactions.layoutManager = LinearLayoutManager(this)
        binding.rvTransactions.adapter = transactionAdapter
    }

    // Atualiza a lista de transações com uma nova lista
    private fun updateTransactionlist(newList: List<Transaction>) {
        TransactionData.transactionList.clear() // Limpa a lista atual
        TransactionData.addListTransaction(newList) // Adiciona os novos dados à lista
        transactionAdapter.notifyDataSetChanged() // Notifica o adaptador sobre as mudanças nos dados
    }

    private fun loadTransactions() {
        // Busca as transações cadastradas e plota na tela
        val savedTransactions = sharedPreferencesTransaction.getTransactions()
        updateTransactionlist(savedTransactions) // Atualiza a lista com os novos dados
    }

    // Função para garantir que o adapter será notificado, independente do que foi alterado na lista
    override fun onStart(){
        super.onStart()
        transactionAdapter.notifyDataSetChanged()
    }
}