package com.example.kt_004_shared_preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_treatment.*

class TreatmentActivity : AppCompatActivity() {

    // Função responsável pela configuração das ações ao carregar a tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)
        // Chamando a função que será responsável por carregar os dados salvos
        loadPrefs()
    }

    // Função que vai carregar os dados salvos pelo usuários
    private fun loadPrefs() {
        // Carregando a biblioteca de preferences nativa do android
        val prefs = this.getSharedPreferences("storageTreatment", Context.MODE_PRIVATE)
        // Carergando as preferencias salvas pelo usuário na tela anterior e armazenando em uma variável
        val name = prefs.getString("name", "")
        val treatment = prefs.getString("treatment", "")

        // Vamos verificar se exite um tratamento configurado
        if (treatment == "Sem tratamento") {
            // Caso nenhum tratamento seja a opção selecioada, vamos exibir apenas o nome
            showTreatment.text = name;
        } else {
            // Se existir um tratamento configurado, vamos concatenar os dados e exibir a mensagem
            val fullText = "$treatment $name";
            showTreatment.text = fullText
        }
    }
}