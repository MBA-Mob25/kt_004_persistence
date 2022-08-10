package com.example.kt_004_shared_preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kt_004_shared_preferences.db.DatabaseManager

import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // Função responsável pela configuração das ações ao carregar a tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Aqui estamos instanciando a conexão com o banco de dados
        val db = DatabaseManager(this, "TREATMENT")

        // Vamos configurar as funções que serão executadas na tela principal
        // Neste caso, ao clicar vamos salvar os dados
        btnSave.setOnClickListener(View.OnClickListener { onSave(db) });
        // Neste caso, ao clicar vamos carregar outra activity e mostrar o resultado
        btnShow.setOnClickListener(View.OnClickListener { onShow() })
    }

    // Função responsável por salvar os dados
    private fun onSave(db: DatabaseManager) {
        // Carregando o nome preenchido
        val name = inputName.text.toString()
        // Carregando o tratamento selecionado
        val treatment = listTreatment.selectedItem.toString()

        // Deletando o registro existente
        db.delete()
        // Salvando os novos dados preenchidos pelo usuario
        db.save(1,name, treatment)

        // Exibindo uma mensagem de sucesso para o usuário
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