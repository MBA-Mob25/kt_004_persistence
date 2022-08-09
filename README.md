# Persistencia de Dados - Aula 4

## Objetivo
Nesta etapa da atividade vamos criar um Aplicativo onde receberemos os dados: Nome do Usuário e Tratamento.

Os dados deverão ser salvos nas preferencias do celular.

---

## Como utilizar o Shared Preferences

Você precisa carregar a instancia na classe que vai utiliza-lo, da seguinte forma:

```kotlin
val prefs = this.getSharedPreferences("storageTreatment", Context.MODE_PRIVATE)
```

Tendo acesso as preferencias do usuários, você vai precisar carregar a instancia que nos permitirá manipular os dados da preferencia

```kotlin
val editor = prefs.edit()
```

Uma vez tendo o objetivo, você pode salvar os dados da seguinte forma

```kotlin
editor.putString("name", inputName.text.toString())
editor.putString("treatment", listTreatment.selectedItem.toString())
```

Pronto, basta rodar o apply e os dados foram salvos!

```kotlin
editor.apply()
```
---

## Consumido os dados salvos nas preferencias
Para acessar, devemos utilizar do objeto já instanciado chamado `prefs`

Para conseguir ler os dados salvos, você deve chamar o método getString, como exemplificado abaixo: 

```kotlin
val name = prefs.getString("name", "")
val treatment = prefs.getString("treatment", "")
```

Pronto, agora basta criar seu layout e exibir a informação como desejado.