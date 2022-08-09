# Persistencia de Dados - Aula 4

## Objetivo
Nesta etapa da atividade vamos criar um Aplicativo onde receberemos os dados: Nome do Usuário e Tratamento.

Os dados deverão ser salvos em um arquivo dentro do celular

---

## Como salvar os dados dentro de um arquivo

Primeiramente, precisamos abrir ou criar um novo arquivo para que possamos salvar os dados. Estamos passando um parametro PRIVATE para garantirmos que este arquvo seja acessado apenas pelo nosso app.

```kotlin
val fs = openFileOutput(filename, MODE_PRIVATE)
```

Tendo acesso as preferencias do usuários, você vai precisar carregar a instancia que nos permitirá manipular os dados da preferencia

```kotlin
val editor = prefs.edit()
```

Uma vez tendo a instancia do arquivo já criado, devemos escreder os dados dentro dele, confetidos em Bytes, da seguinte forma

```kotlin
fs.write(data.toByteArray())
```

Pronto, agora que temos os dados salvos, podemos fechar o arquivo

```kotlin
fs.close()
```
---

## Carregando os dados salvos no arquivo

Uma vez que temos o arquivo criado, podemos acessa-lo de uma forma bem semelhante. 

Primeiramente precisamos carregar o arquivo "abri-lo" para podermos acessar oque temos internamente.

```kotlin
val fi = openFileInput(filename)
```

Agora podemos acessar estes dados de forma simples, vamos abrir o arquivo criado para carregar estes dados

```kotlin
val data = fi.readBytes()
```

Bem, agora basta fechar o arquivo desta forma:

```kotlin
fi.close()
```

Bem, agora que temos os dados já carregados, nós precisamos converte-los em string e assim poderemos manipula-lo com sucesso!

```koltin
data.toString(Charset.defaultCharset())
```

Pronto, agora basta criar seu layout e exibir a informação como desejado.