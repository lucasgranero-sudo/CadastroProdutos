package br.unisanta.produtos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.unisanta.produtos.adapter.ProdutoAdapter
import br.unisanta.produtos.databinding.ActivityListaProdutosBinding
import br.unisanta.produtos.model.Produto
import com.google.firebase.firestore.FirebaseFirestore

// Tela que busca todos os produtos do Firestore e mostra em uma lista
class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaProdutosBinding
    private val db = FirebaseFirestore.getInstance()
    private val adapter = ProdutoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvProdutos.layoutManager = LinearLayoutManager(this)
        binding.rvProdutos.adapter = adapter

        binding.btnVoltar.setOnClickListener {
            finish()
        }

        carregarProdutos()
    }

    private fun carregarProdutos() {
        db.collection("Produtos")
            .get()
            .addOnSuccessListener { resultado ->
                val lista = mutableListOf<Produto>()
                for (documento in resultado) {
                    val produto = Produto(
                        id = documento.id,
                        nome = documento.getString("Nome") ?: "",
                        categoria = documento.getString("Categoria") ?: "",
                        preco = documento.getDouble("Preço") ?: 0.0
                    )
                    lista.add(produto)
                }
                adapter.atualizarLista(lista)
                binding.txtTotal.text = "Total de produtos: " + lista.size
                binding.txtVazio.visibility = if (lista.isEmpty()) View.VISIBLE else View.GONE
            }
            .addOnFailureListener { erro ->
                Toast.makeText(this, "Erro ao buscar produtos: " + erro.message, Toast.LENGTH_LONG).show()
            }
    }
}
