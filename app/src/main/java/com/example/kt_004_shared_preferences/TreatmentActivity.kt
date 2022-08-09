package com.example.kt_004_shared_preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_treatment.*
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class TreatmentActivity : AppCompatActivity() {

    // Função responsável pela configuração das ações ao carregar a tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)
        // Chamando a função que será responsável por carregar os dados salvos
        loadDataFile()
    }

    // Função que vai carregar os dados salvos pelo usuários
    private fun loadDataFile() {
        // Executando função de apoio para carregar os dados salvos em arquivo
        val data = recoveryData("treatment")
        // Como salvamos os dados separados por ":" vamos separa-los para termos os dados definidos
        val tokenizer = StringTokenizer(data, ":")
        // Pegando o nome, primeiro dado salvo no arquivo
        val name = if (tokenizer.hasMoreTokens()) tokenizer.nextToken() else "Sem nome"
        // Pegando o tratamento, segundo dado salvo no arquivo
        val treatment = if (tokenizer.hasMoreTokens()) tokenizer.nextToken() else "Sem tratamento"

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

    // Função responsável por ler o arquivo e transforma-lo em string
    private fun recoveryData(filename: String): String {
        return try {
            // Vamos carregar o arquivo salvo pelo nome
            val fi = openFileInput(filename)
            // Vamos carregar os dados que foram salvos dentro do arquivo gerado
            val data = fi.readBytes()
            // Para salvar memória, vamos fechar o arquivo aberto, sabendo que já temos os dados na variável de data
            fi.close()
            // vamos converter os dados de byte para String, utilizando o charset padrão do aparelho do usuário
            data.toString(Charset.defaultCharset())
        } catch (e: FileNotFoundException) {
            // Caso não exista um arquvio ele retornava os dados vazio
            ""
        } catch (e: IOException) {
            // Caso ocorra algum erro ele também retornava os dados vazio
            ""
        }
    }
}