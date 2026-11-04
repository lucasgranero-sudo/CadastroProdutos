package br.unisanta.produtos.model

// Classe que representa um produto salvo no Firestore
data class Produto(
    val id: String = "",
    val nome: String = "",
    val categoria: String = "",
    val preco: Double = 0.0
)
