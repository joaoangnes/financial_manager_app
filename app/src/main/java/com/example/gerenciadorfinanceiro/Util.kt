package com.example.gerenciadorfinanceiro

import android.content.Context
import android.widget.Toast
import java.util.UUID

class Util {
    companion object {
        // exibe um toast com base no contexto e string recebidos via parametro
        fun exibirToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        // Função para gerar um ID único
        fun generateUniqueId(): String {
            return UUID.randomUUID().toString()
        }
    }

    object Chaves{
        const val PREFENCES_MASTER_KEY = "MyPrefs"
        const val CATEGORY_LIST_KEY = "category_list"
        const val TRANSACTION_LIST_KEY  = "transaction_list"
    }
}