# Cadastro de Produtos

App Android em Kotlin que cadastra produtos no **Cloud Firestore** e mostra todos os produtos cadastrados em uma lista.

## Firestore
- **Coleção:** `Produtos`
- **Documento:** ID autogerado (`add()`)
- **Dados:**
  - `Nome` (texto)
  - `Categoria` (texto)
  - `Preço` (número)

## Telas
- **MainActivity** – tela de cadastro com os campos Nome, Categoria e Preço. O botão **Salvar** grava o produto na coleção `Produtos`. O botão **Ver produtos cadastrados** abre a lista.
- **ListaProdutosActivity** – busca todos os documentos da coleção `Produtos` (`get()`) e mostra em um RecyclerView com nome, categoria e preço.

## O que foi usado
- Kotlin + View Binding
- RecyclerView com Adapter (`adapter/ProdutoAdapter.kt`)
- Firebase Firestore (`com.google.firebase:firebase-firestore`)
- Arquivo `app/google-services.json` do projeto Firebase **CadastroProdutos**

## Como rodar
Abrir no Android Studio, esperar o Gradle sincronizar e rodar no emulador ou celular (precisa de internet).
