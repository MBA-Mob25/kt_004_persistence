package com.example.kt_004_shared_preferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Função responsável pela configuração das ações ao carregar a tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Vamos configurar as funções que serão executadas na tela principal
        // Neste caso, ao clicar vamos salvar os dados
        btnSave.setOnClickListener(View.OnClickListener { onSave() });
        // Neste caso, ao clicar vamos carregar outra activity e mostrar o resultado
        btnShow.setOnClickListener(View.OnClickListener { onShow() })
    }

    // Função responsável por salvar os dados
    private fun onSave() {
        // Carregando a biblioteca de preferences nativa do android
        val prefs = this.getSharedPreferences("storageTreatment", Context.MODE_PRIVATE)
        // Pegando o objeto responsável pela edição dos dados dentro das preferencias do sistema
        val editor = prefs.edit()

        // Adicionando os dados preenchidos pelo usuário
        editor.putString("name", inputName.text.toString())
        editor.putString("treatment", listTreatment.selectedItem.toString())

        // Aplicando os dados de preferencia / salvando na memória
        editor.apply()

        // Exibindo uma mensagem para o usuário dizendo que tudo foi salvo com sucesso
        Toast.makeText(this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
    }

    // Função responsável pela navegação até a exibição da tela de saudação
    private fun onShow() {
        // Carregando a intenção da nova tela para o usuário navegar
        val intent = Intent(this, TreatmentActivity::class.java)
        // Executando a navegação para nova tela
        startActivity(intent)
    }

}