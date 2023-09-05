package com.example.gerenciadorfinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper.Companion.sharedPreferencesCategory
import com.example.gerenciadorfinanceiro.databinding.ActivityAddCategoryBinding

class ActivityAddCategory : AppCompatActivity() {
    private lateinit var binding: ActivityAddCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter

    //private val categoryList = mutableListOf<Category>() // Lista das categorias cadastradas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout da atividade
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o SharedPreferences para armazenar as categorias
        sharedPreferencesCategory = SharedPreferencesHelper(this)

        // Configura o RecyclerView
        setupRecyclerView()

        // Configura o botão "Salvar Categoria" para adicionar uma categoria
        binding.btnSaveCategory.setOnClickListener {
            addCategory() // Adiciona a categoria
        }

        // Configura o botão "Atualizar Lista" para carregar as categorias
        binding.btnListRefresh.setOnClickListener {
            loadCategory()
        }

        // Carrega a lista de categorias na tela
        loadCategory()
    }

    // Atualiza a lista de categorias com uma nova lista
    private fun updateCategoryList(newList: List<Category>) {
        CategoryData.categoryList.clear() // Limpa a lista atual
        CategoryData.addListCategory(newList) // Adiciona os novos dados à lista
        categoryAdapter.notifyDataSetChanged() // Notifica o adaptador sobre as mudanças nos dados
    }

    // Configura o RecyclerView
    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter(this)
        binding.rvCategories.layoutManager = LinearLayoutManager(this)
        binding.rvCategories.adapter = categoryAdapter
    }

    // Carrega as categorias do SharedPreferences e atualiza a lista
    private fun loadCategory() {
        // Busca as categorias cadastradas e plota na tela
        val savedCategories = sharedPreferencesCategory.getCategories()
        updateCategoryList(savedCategories) // Atualiza a lista com os novos dados
    }

    // Função que irá adicionar a categoria no SharedPreferences
    private fun addCategory() {
        val categoryName = binding.edtCategoryName.text.toString().trim()

        // Verifica se foi preenchido o nome da categoria
        if (binding.edtCategoryName.text.isEmpty()) {
            Util.exibirToast(this, "Por favor, insira uma categoria")
            return
        }

        // Adiciona o objeto categoria à lista de categorias
        val newCategory = Category(categoryName)
        CategoryData.addCategory(newCategory) //.add(newCategory)
        categoryAdapter.notifyItemInserted(CategoryData.getListSize())

        // Salva a nova categoria no SharedPreferences
        sharedPreferencesCategory.saveCategories(CategoryData.categoryList)

        // Limpa o EditText
        binding.edtCategoryName.text.clear()

        Util.exibirToast(this, "Categoria adicionada com sucesso!")
    }

    // Função para garantir que o adapter será notificado, independente do que foi alterado na lista
    override fun onStart(){
        super.onStart()
        categoryAdapter.notifyDataSetChanged()
    }
}
