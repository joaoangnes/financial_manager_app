package com.example.gerenciadorfinanceiro

// Classe responsável por gerenciar as lista de categorias do aplicativo
class CategoryData {
    companion object CategoryData {
        var categoryList = mutableListOf<Category>() // Lista das categorias cadastradas

        // Função para adicionar uma nova categoria para a lista
        fun addCategory(category: Category){
            categoryList.add(category)
        }

        fun addListCategory(list: List<Category>){
            categoryList.addAll(list)
        }

        // Função que retorna o tamanho da lista de categorias
        fun getListSize(): Int{
            return categoryList.size
        }

    }
}