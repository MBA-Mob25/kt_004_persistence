# Persistencia de Dados - Aula 4

## Objetivo
Nesta etapa da atividade vamos criar um Aplicativo onde receberemos os dados: Nome do Usuário e Tratamento.

Os dados serão salvo dentro do banco de dados SQLite

---

## Como criar um banco de dados SQLite em seu APP

Primeiramente, precisamos criar uma classe `DatabaseManager` para que possamos manipular os dados

Ao criar essa classe, precisamos configuras as funções obrigatórias chamadas: `onCreate` e `onUpgrade`

```kotlin
override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE TREATMENT(" +
                "ID INT NOT NULL," +
                "NAME VARCHAR(45) NOT NULL," +
                "TREATMENT VARCHAR(45) NOT NULL," +
                "PRIMARY KEY (ID)" +
            ")"
        )
    }
```

```kotlin
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS TREATMENT")
        p0?.execSQL(
            "CREATE TABLE TREATMENT(" +
                "ID INT NOT NULL," +
                "NAME VARCHAR(45) NOT NULL," +
                "TREATMENT VARCHAR(45) NOT NULL," +
                "PRIMARY KEY (ID)" +
            ")"
        )
    }
```

Uma vez tendo a classe de gerencia dos dados, podemos instancia-la da seguinte forma

```kotlin
val db = DatabaseManager(this, "NOME_TABELA")
```

Pronto, agora temos acesso ao banco de dados SQLite em nosso aplicativo
---

## Criando os métodos de manipulação do banco de dados dentro do gerenciados

Para gente inserir os dados em nosso banco, devemos utilizar da seguinte forma:

```kotlin
fun save(id: Int, name: String, treatment: String) {
    val db = this.writableDatabase
    val cv = ContentValues()
    cv.put("ID", id)
    cv.put("NAME", name)
    cv.put("TREATMENT", treatment)
    db.insert("TREATMENT", "ID", cv)
}
```

Para você listar os dados já existentes no banco de dados, você precisa criar um Cursos que retorna os dados atraves de uma query como:

```kotlin
fun select(): Cursor {
    val db = this.writableDatabase
    return db.rawQuery("SELECT NAME, TREATMENT FROM TREATMENT", null)
}
```

Para finalizar, você pode deletar o dado com o identificador dele da seguinte forma:

```kotlin
fun delete() {
    val db = this.writableDatabase
    db.delete("TREATMENT", "ID=1", null)
}
```

Pronto, agora que temos os dados salvos, podemos fechar o arquivo