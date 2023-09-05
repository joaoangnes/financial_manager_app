package com.example.gerenciadorfinanceiro

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper.Companion.sharedPreferencesCategory
import com.example.gerenciadorfinanceiro.databinding.ItemCategoryBinding
//, private val categoryList: List<Category>
// Classe Adapter para a RecyclerView que exibe as categorias
class CategoryAdapter(private val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // Método chamado quando uma nova ViewHolder é criada
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, sharedPreferencesCategory)
    }

    // Método chamado para vincular os dados a uma ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = CategoryData.categoryList[position]
        holder.bind(category)
    }

    // Retorna o número total de itens na lista
    override fun getItemCount(): Int {
        return CategoryData.getListSize()
    }

    // Classe ViewHolder interna para configurar a exibição de cada item na RecyclerView
    inner class ViewHolder(private val binding: ItemCategoryBinding, private val sharedPreferencesCategory: SharedPreferencesHelper) : RecyclerView.ViewHolder(binding.root) {
        init {
            // Configura o evento de clique no botão de edição
            binding.ivEditTransaction.setOnClickListener {
                val category = CategoryData.categoryList[adapterPosition]
                val newCategory = Category("Rubinho") // Exemplo de uma nova categoria
                sharedPreferencesCategory.editCategory(category, newCategory)

                Util.exibirToast(context, "Categoria editada: ${category.name} -> ${newCategory.name}")

                // Registra um log para mostrar a edição da categoria
                Log.d("SharedPreferencesHelper", "Categoria editada: ${category.name} -> ${newCategory.name}")
                // Implemente sua lógica de edição aqui
            }

            // Configura o evento de clique no botão de exclusão
            binding.ivDeleteTransaction.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val category = CategoryData.categoryList[position]
                    sharedPreferencesCategory.deleteCategory(category)

                    Util.exibirToast(context, "Categoria excluída!")
                    // Registra um log para mostrar a exclusão da categoria
                    Log.d("SharedPreferencesHelper", "Categoria excluída: ${category.name}")
                }
            }
        }

        // Método para vincular os dados da categoria aos elementos da ViewHolder
        fun bind(category: Category) {
            binding.txtCategoryName.text = " - ${category.name}"
        }
    }
}
