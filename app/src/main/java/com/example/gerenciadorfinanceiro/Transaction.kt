package com.example.gerenciadorfinanceiro

// Classe para as transações do aplicativo
class Transaction(val transaction_id: String, val description: String, val value: Double, val category: Category) {
}