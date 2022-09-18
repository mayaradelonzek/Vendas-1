package br.com.dionataferraz.vendas

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.databinding.ItemListBinding
import br.com.dionataferraz.vendas.transaction.TransactionType
import br.com.dionataferraz.vendas.transaction.data.Transaction

class TransactionAdapter(private val listener: Listener) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    interface Listener {
        fun onItemClick(text: String)
    }

    private val listItem: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun addNewList(list: List<Transaction>) {
        listItem.clear()
        notifyItemRangeRemoved(0, listItem.size)
        listItem.addAll(list)
    }

    fun addList(list: List<Transaction>) {
        listItem.addAll(list)
    }

    fun updateItem(item: Transaction, position: Int) {
        listItem[position] = item
        notifyItemChanged(position)
    }

}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
): RecyclerView.ViewHolder(binding.root) {

    fun getIconByTransactionType(type: TransactionType): Int {
        var icon = R.drawable.ic_baseline_attach_money_24

        when {
            TransactionType.BILL.equals(type) -> icon = R.drawable.ic_baseline_attach_money_24
            TransactionType.LEISURE.equals(type) -> icon = R.drawable.ic_baseline_videogame_asset_24
            TransactionType.MARKET.equals(type) -> icon = R.drawable.ic_baseline_shopping_cart_24
        }

        return icon
    }

    @SuppressLint("SetTextI18n")
    fun bind(transaction: Transaction) {
        binding.tvName.text = transaction.name
        binding.tvTime.text = transaction.time.hours.toString() + ":" + transaction.time.minutes.toString()
        binding.tvAmount.text = "R$ " + transaction.amount.toString()
        binding.icon.setCompoundDrawablesWithIntrinsicBounds(getIconByTransactionType(transaction.type), 0, 0, 0)
        binding.root.setOnClickListener {
//            listener.onItemClick(transaction)
        }
    }
}