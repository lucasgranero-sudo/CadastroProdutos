package br.unisanta.produtos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.produtos.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

// Tela de cadastro de produtos
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSalvar.setOnClickListener {
            salvarProduto()
        }

        binding.btnListar.setOnClickListener {
            startActivity(Intent(this, ListaProdutosActivity::class.java))
        }
    }

    private fun salvarProduto() {
        val nome = binding.edtNome.text.toString().trim()
        val categoria = binding.edtCategoria.text.toString().trim()
        val precoTexto = binding.edtPreco.text.toString().trim().replace(",", ".")

        if (nome.isEmpty() || categoria.isEmpty() || precoTexto.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val preco = precoTexto.toDoubleOrNull()
        if (preco == null) {
            Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show()
            return
        }

        // dados do produto
        val produto = hashMapOf(
            "Nome" to nome,
            "Categoria" to categoria,
            "Preço" to preco
        )

        // coleção Produtos, o add() cria o documento com id autogerado
        db.collection("Produtos")
            .add(produto)
            .addOnSuccessListener { documento ->
                Toast.makeText(this, "Produto cadastrado! ID: " + documento.id, Toast.LENGTH_SHORT).show()
                limparCampos()
            }
            .addOnFailureListener { erro ->
                Toast.makeText(this, "Erro ao cadastrar: " + erro.message, Toast.LENGTH_LONG).show()
            }
    }

    private fun limparCampos() {
        binding.edtNome.setText("")
        binding.edtCategoria.setText("")
        binding.edtPreco.setText("")
        binding.edtNome.requestFocus()
    }
}
