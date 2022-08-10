package com.example.kt_004_shared_preferences

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kt_004_shared_preferences.db.DatabaseManager
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

        // Aqui estamos instanciando a conexão com o banco de dados
        val db = DatabaseManager(this, "TREATMENT")

        // Chamando a função que será responsável por carregar os dados salvos
        loadDataFile(db)
    }

    // Função que vai carregar os dados salvos pelo usuários
    @SuppressLint("Range")
    private fun loadDataFile(db: DatabaseManager) {
        // Criando as variavéis para preenchimento dos dados que serão carregados no banco de dados
        var name = ""
        var treatment = ""

        // Resgatando os dados existente no banco de dados
        val cursor = db.select()
        // Caso existe dados na tabela, vamos preencher os dados nas variaveis criados
        if (cursor.count > 0) {
            // Carregando o primeiro registro da listagem
            cursor.moveToFirst()
            // Pegando o dado "Name" salvado no banco de dados
            name = cursor.getString(cursor.getColumnIndex("NAME"))
            // Pegando o dado "Tratamento" salvado no banco de dados
            treatment = cursor.getString(cursor.getColumnIndex("TREATMENT"))
        }

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