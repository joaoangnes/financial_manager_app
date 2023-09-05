package com.example.gerenciadorfinanceiro

// Classe responsável por gerenciar as lista de transações do aplicativo
class TransactionData {
    companion object TransactionData {
        var transactionList = mutableListOf<Transaction>() // Lista das categorias cadastradas

        // Função para adicionar uma nova categoria para a lista
        fun addTransaction(transaction: Transaction){
            transactionList.add(transaction)
        }

        // Função para adicionar uma lista de transações de uma vez
        fun addListTransaction(list: List<Transaction>){
            transactionList.addAll(list)
        }

        // Função que retorna o tamanho da lista de categorias
        fun getListSize(): Int{
            return transactionList.size
        }

    }
}