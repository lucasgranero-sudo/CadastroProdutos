package br.unisanta.produtos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.produtos.databinding.ItemProdutoBinding
import br.unisanta.produtos.model.Produto
import java.util.Locale

class ProdutoAdapter : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    private var lista: List<Produto> = emptyList()

    fun atualizarLista(novaLista: List<Produto>) {
        lista = novaLista
        notifyDataSetChanged()
    }

    class ProdutoViewHolder(val binding: ItemProdutoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemProdutoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = lista[position]
        holder.binding.txtNome.text = produto.nome
        holder.binding.txtCategoria.text = "Categoria: " + produto.categoria
        holder.binding.txtPreco.text = "R$ " + String.format(Locale("pt", "BR"), "%.2f", produto.preco)
    }

    override fun getItemCount(): Int = lista.size
}
