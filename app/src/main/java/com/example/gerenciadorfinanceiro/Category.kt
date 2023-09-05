package com.example.gerenciadorfinanceiro

// Classe para as categorias financeiras do aplicativo
class Category(var category_id: String, var name: String) {
    // Construtor da classe Category que recebe o nome da categoria como parâmetro

    // Reescrita da função equals para comparar duas categorias
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Verifica se as duas referências são iguais
        if (other !is Category) return false // Verifica se o objeto é uma instância de Category
        return name == other.name // Compara os nomes das categorias
    }

    // Reescrita da função hashCode para gerar um código hash baseado no nome da categoria
    override fun hashCode(): Int {
        return name.hashCode() // Gera um código hash com base no nome da categoria
    }
}
