package com.example.gerenciadorfinanceiro.Helper

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.gerenciadorfinanceiro.Category
import com.example.gerenciadorfinanceiro.Transaction
import com.example.gerenciadorfinanceiro.Util
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Classe para gerenciar SharedPreferences relacionados a categorias
class SharedPreferencesHelper(context: Context) {


    // Referência ao SharedPreferences
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(Util.Chaves.PREFENCES_MASTER_KEY, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    // Variável global para uma instância específica de SharedPreferencesHelper
    companion object {
        lateinit var sharedPreferencesCategory: SharedPreferencesHelper
        lateinit var sharedPreferencesTransaction: SharedPreferencesHelper
    }

    // Método para salvar uma string no SharedPreferences
    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    // Método para recuperar uma string do SharedPreferences
    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    // Método para limpar todas as preferências
    fun clearPreferences() {
        editor.clear()
        editor.apply()
    }

    // Método para salvar a lista de transações no SharedPreferences
    fun saveTransaction(transaction: List<Transaction>) {
        val gson = Gson()
        val json = gson.toJson(transaction)
        editor.putString(Util.Chaves.TRANSACTION_LIST_KEY, json)
        editor.apply()
    }

    // Método para salvar a lista de categorias no SharedPreferences
    fun saveCategories(categories: List<Category>) {
        val gson = Gson()
        val json = gson.toJson(categories)
        editor.putString(Util.Chaves.CATEGORY_LIST_KEY, json)
        editor.apply()
    }

    // Método para obter a lista de transações do SharedPreferences
    fun getTransactions(): List<Transaction> {
        val gson = Gson()
        val json = sharedPreferences.getString(Util.Chaves.TRANSACTION_LIST_KEY, null)
        val type = object : TypeToken<List<Transaction>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    // Método para obter a lista de categorias do SharedPreferences
    fun getCategories(): List<Category> {
        val gson = Gson()
        val json = sharedPreferences.getString(Util.Chaves.CATEGORY_LIST_KEY, null)
        val type = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    // Método para editar uma categoria existente
    fun editCategory(oldCategory: Category, newCategory: Category) {
        val categories = getCategories().toMutableList()
        categories.remove(oldCategory)
        categories.add(newCategory)
        saveCategories(categories)
    }

    // Método para excluir uma categoria
    fun deleteCategory(category: Category) {
        val categories = getCategories().toMutableList()
        categories.remove(category)
        saveCategories(categories)
    }

    // Método para remover todos os dados das SharedPreferences
    fun clearAllData() {
        editor.clear()
        editor.apply()
    }

    // Método para excluir uma transação
    fun deleteTransaction(transaction: Transaction) {
        val transactions = getTransactions().toMutableList()
        transactions.remove(transaction)
        saveTransaction(transactions)
    }
}
