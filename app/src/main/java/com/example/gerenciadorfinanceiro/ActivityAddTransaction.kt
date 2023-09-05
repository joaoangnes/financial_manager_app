package com.example.gerenciadorfinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper.Companion.sharedPreferencesCategory
import com.example.gerenciadorfinanceiro.Helper.SharedPreferencesHelper.Companion.sharedPreferencesTransaction
import com.example.gerenciadorfinanceiro.databinding.ActivityAddTransactionBinding

class ActivityAddTransaction : AppCompatActivity() {
    private lateinit var binding: ActivityAddTransactionBinding
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var sharedPreferencesCategory: SharedPreferencesHelper
    private lateinit var sharedPreferencesTransaction: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa os SharedPreferencesHelper
        sharedPreferencesCategory = SharedPreferencesHelper(this)
        sharedPreferencesTransaction = SharedPreferencesHelper(this)
        transactionAdapter = TransactionAdapter(this)

        // Configura o Spinner com as categorias cadastradas
        configureCategorySpinner()

        // Configura o botão "Salvar Transação" para adicionar uma transação
        binding.btnSaveTransaction.setOnClickListener {
            addTransaction()
        }

        // Redireciona o usuário para o cadastro de categorias do aplicativo
        binding.btnGoAddCategoryActivity.setOnClickListener{
            startActivity(Intent(this, ActivityAddCategory::class.java))
        }

        // Redireciona o usuário para a lista de transações do aplicativo
        binding.btnGoListTransaction.setOnClickListener{
            startActivity(Intent(this, ActivityListTransactions::class.java))
        }
    }

    // Função que irá adicionar a transação no SharedPreferences
    private fun addTransaction() {
        val transactionDescription = binding.edtTransactionDescription.text.toString()
        val transactionValue = binding.edtTransactionValue.text.toString().toDouble()
        val selectedCategoryIndex = binding.spnCategory.selectedItemPosition
        val savedTransactions = sharedPreferencesTransaction.getTransactions()
        TransactionData.addListTransaction(savedTransactions)

        // Verifica se foi preenchido os dados da transação
        if ( binding.edtTransactionDescription.text.isEmpty()||
             binding.edtTransactionValue.text.isEmpty()) {
            Util.exibirToast(this, "Por favor, insira todas as informações.")
            return
        }

        // Verifique se a categoria selecionada é válida
        if (selectedCategoryIndex >= 0 && selectedCategoryIndex < CategoryData.getListSize()) {
            val selectedCategory = CategoryData.categoryList[selectedCategoryIndex]

            // Instanciando o objeto de transação
            val newTransaction = Transaction(transactionDescription, transactionValue, selectedCategory)

            // Salve a transação em SharedPreferences ou em outra fonte de dados
            TransactionData.addTransaction(newTransaction)

           // transactionAdapter = TransactionAdapter(this)
            // transactionAdapter.notifyDataSetChanged()

            // Limpe os campos após salvar a transação
            binding.edtTransactionDescription.text.clear()
            binding.edtTransactionValue.text.clear()
            binding.spnCategory.setSelection(0) // Defina a seleção do Spinner de volta para o primeiro item
        }

        sharedPreferencesCategory = SharedPreferencesHelper(this) // Inicializa o SharedPreferences para armazenar as categorias
        sharedPreferencesTransaction.saveTransaction(TransactionData.transactionList) // Salva a nova transação no SharedPreferences

        // Limpa o EditText
        binding.edtTransactionValue.text.clear()
        Util.exibirToast(this,"Transação cadastrada com sucesso!")
    }

    // Função que irá adicionar as categorias no elemento spinner no cadastro de transação
    private fun configureCategorySpinner() {
        // Limpa a lista atual para evitar duplicatas
        CategoryData.categoryList.clear()

        // Carrega as categorias do SharedPreferences
        val savedCategories = sharedPreferencesCategory.getCategories()
        CategoryData.addListCategory(savedCategories)

        // Cria um ArrayAdapter para o Spinner
        val categoryNames = CategoryData.categoryList.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spnCategory.adapter = adapter
    }

    // Função para que quando voltar para activity ele atualizar os dados de categoria
    override fun onResume() {
        super.onResume()
        Log.d("Resume", "Voltou para a tela")

        // Atualiza as informações do spinner de categorias
        configureCategorySpinner()
    }

    // Função para garantir que o adapter será notificado, independente do que foi alterado na lista
    override fun onStart(){
        super.onStart()
        transactionAdapter.notifyDataSetChanged()
    }

}

