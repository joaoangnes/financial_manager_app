package com.example.gerenciadorfinanceiro

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadorfinanceiro.view.CategoryViewHolder

//, private val categoryList: List<Category>
// Classe Adapter para a RecyclerView que exibe as categorias
class CategoryAdapter(private val context: Context) : RecyclerView.Adapter<CategoryViewHolder>() {

    // Método chamado quando uma nova ViewHolder é criada
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var categoryView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(categoryView)
    }

    // Método chamado para vincular os dados a uma ViewHolder
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var currentItem = CategoryData.categoryList[position]
        holder.txtCategoryName.text = " - ${currentItem.name}"
    }

    // Retorna o número total de itens na lista
    override fun getItemCount(): Int {
        return CategoryData.getListSize()
    }

}
