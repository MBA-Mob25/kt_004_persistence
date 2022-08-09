package com.example.kt_004_shared_preferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException
import java.io.IOException

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
        val data = inputName.text.toString() + ":" + listTreatment.selectedItem.toString()
        saveFile("treatment", data);
        Toast.makeText(this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
    }

    // Função de apoio a função de salvar dados, esta função tem como objetivo criar o arquivo e adicionar os dados dentro dele
    private fun saveFile(filename: String, data: String) {
        try {
            // Criando a instancia do arquivo para podermos gradas os dados
            val fs = openFileOutput(filename, MODE_PRIVATE)
            // Escrevendo os dados em forma de bits no arquivo criado
            fs.write(data.toByteArray())
            // Após finalizar o arquivo, vamos fecha-lo
            fs.close()
        } catch (e: FileNotFoundException) {
            // Em caso de erro do tipo arquivo não localizado, vamos emitit um log
            Log.i("saveFile", "FileNotFoundException")
        } catch (e: IOException) {
            // Em caso de um erro generico, vamos emitir outro log
            Log.i("saveFile", "IOException")
        }
    }

    // Função responsável pela navegação até a exibição da tela de saudação
    private fun onShow() {
        // Carregando a intenção da nova tela para o usuário navegar
        val intent = Intent(this, TreatmentActivity::class.java)
        // Executando a navegação para nova tela
        startActivity(intent)
    }

}