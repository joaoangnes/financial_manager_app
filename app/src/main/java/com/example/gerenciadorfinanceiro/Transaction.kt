package com.example.gerenciadorfinanceiro

// Classe para as transações do aplicativo
class Transaction(var id: String, val description: String, val value: Double, val category: Category) {

    // Sobrescreve a função equals para comparar transações com base no ID
    override fun equals(other: Any?): Boolean {
        // Verifica se 'other' é uma referência para o mesmo objeto (mesma memória)
        if (this === other) return true

        // Verifica se 'other' é uma instância de Transaction
        if (javaClass != other?.javaClass) return false

        // Converte 'other' para Transaction
        other as Transaction

        // Compara as transações com base no ID
        return id == other.id
    }

    // Sobrescreve a função hashCode para usar o ID como código de hash
    override fun hashCode(): Int {
        // Usa o código de hash do ID para identificar a transação
        return id.hashCode()
    }
}
