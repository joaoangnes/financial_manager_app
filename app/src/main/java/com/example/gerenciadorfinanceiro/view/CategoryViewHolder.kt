package com.example.gerenciadorfinanceiro.view

import android.app.Dialog
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
    private var ivEditCategory: ImageView = categoryView.findViewById(R.id.ivEditCategory)
    private var ivDeleteCategory: ImageView = categoryView.findViewById(R.id.ivDeleteCategory)

    init {

        // Configura o evento de clique no botão de edição
        ivEditCategory.setOnClickListener {
            val category = CategoryData.categoryList.getOrNull(adapterPosition)

            if (category != null) {
                val dialog = Dialog(categoryView.context)
                dialog.setContentView(R.layout.dialog_edit_category)

                val edtNewCategoryName = dialog.findViewById<EditText>(R.id.edtNewCategoryName)
                val btnSaveCategory = dialog.findViewById<Button>(R.id.btnSaveCategory)

                edtNewCategoryName.setText(category.name)

                btnSaveCategory.setOnClickListener {
                    val newCategoryName = edtNewCategoryName.text.toString()

                    if (newCategoryName.isNotEmpty()) {
                        sharedPreferencesCategory.editCategoryName(category.id, newCategoryName)

                        dialog.dismiss()

                        Util.exibirToast(categoryView.context, "Categoria editada: ${category.name} -> $newCategoryName")

                    } else {
                        Util.exibirToast(categoryView.context, "O nome da categoria não pode estar vazio.")
                    }
                }

                dialog.show()
            }
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