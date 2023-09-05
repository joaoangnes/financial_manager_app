package com.example.gerenciadorfinanceiro.view

import android.app.Dialog
import android.graphics.drawable.Icon
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gerenciadorfinanceiro.Category
import com.example.gerenciadorfinanceiro.CategoryData
import com.example.gerenciadorfinanceiro.R
import com.example.gerenciadorfinanceiro.Util
import com.example.gerenciadorfinanceiro.helper.SharedPreferencesHelper.Companion.sharedPreferencesCategory

// Classe de View Holder para as Categorias
class CategoryViewHolder(categoryView: View): ViewHolder(categoryView) {
    var txtCategoryName: TextView = categoryView.findViewById(R.id.txtCategoryName)
    var ivEditCategory: ImageView = categoryView.findViewById(R.id.ivEditCategory)
    var ivDeleteCategory: ImageView = categoryView.findViewById(R.id.ivDeleteCategory)
    //binding.txtCategoryName.text = " - ${category.name}"

    init {
        // Configura o evento de clique no botão de edição
        ivEditCategory.setOnClickListener {
            val category = CategoryData.categoryList[adapterPosition]

            // Cria um diálogo de edição
            val dialog = Dialog(categoryView.context)
            dialog.setContentView(R.layout.dialog_edit_category)

            val edtNewCategoryName = dialog.findViewById<EditText>(R.id.edtNewCategoryName)
            val btnSaveCategory = dialog.findViewById<Button>(R.id.btnSaveCategory)

            // Preenche o campo de texto com o nome atual da categoria
            edtNewCategoryName.setText(category.name)

            btnSaveCategory.setOnClickListener {
                val newCategoryName = edtNewCategoryName.text.toString()

                // Verifica se o nome não está vazio
                if (newCategoryName.isNotEmpty()) {
                    // Atualiza o nome da categoria
                    val newCategory = Category(Util.generateUniqueId(), newCategoryName)
                    sharedPreferencesCategory.editCategory(category, newCategory)

                    // Fecha o diálogo
                    dialog.dismiss()

                    // Exibe uma mensagem ou atualiza a interface conforme necessário
                    Util.exibirToast(categoryView.context, "Categoria editada: ${category.name} -> $newCategoryName")

                    // Registra um log para mostrar a edição da categoria
                    Log.d("SharedPreferencesHelper", "Categoria editada: ${category.name}")
                } else {
                    // Exibe uma mensagem de erro se o nome estiver vazio
                    Util.exibirToast(categoryView.context, "O nome da categoria não pode estar vazio.")
                }
            }

            // Exibe o diálogo
            dialog.show()
        }


        // Configura o evento de clique no botão de exclusão
        ivDeleteCategory.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val category = CategoryData.categoryList[position]
                sharedPreferencesCategory.deleteCategory(category)
                Util.exibirToast(categoryView.context, "Categoria excluída!")
            }
        }
    }
}