package br.com.dionataferraz.vendas

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.account.data.local.Operation
import br.com.dionataferraz.vendas.databinding.ItemListBinding
import br.com.dionataferraz.vendas.transaction.data.TransactionEntity
import java.text.SimpleDateFormat
import java.util.*

class TransactionAdapter(private val listener: Listener) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    interface Listener {
        fun onItemClick(text: String)
    }

    private val listItem: MutableList<TransactionEntity> = mutableListOf()

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

    fun addNewList(list: List<TransactionEntity>) {
        listItem.clear()
        notifyItemRangeRemoved(0, listItem.size)
        listItem.addAll(list)
    }

    fun addList(list: List<TransactionEntity>) {
        listItem.addAll(list)
    }

    fun updateItem(item: TransactionEntity, position: Int) {
        listItem[position] = item
        notifyItemChanged(position)
    }

}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
): RecyclerView.ViewHolder(binding.root) {

    fun getIconByTransactionType(type: Operation): Int {
        var icon = R.drawable.ic_baseline_arrow_drop_down_24

        when {
            Operation.WITHDRAW.equals(type) -> icon = R.drawable.ic_baseline_arrow_drop_down_24
            Operation.DEPOSIT.equals(type) -> icon = R.drawable.ic_baseline_arrow_drop_up_24
        }

        return icon
    }

    @SuppressLint("SimpleDateFormat")
    fun getFormatedDate(date: Date): String {
        val loc = Locale("pt", "BR")
        return SimpleDateFormat("dd MMM yyyy HH:mm", loc).format(date)
    }

    fun getFormatedName(operation: Operation): String {
        var name = ""

        when {
            Operation.WITHDRAW.equals(operation) -> name = "Dinheiro resgatado"
            Operation.DEPOSIT.equals(operation) -> name = "Depósito"
        }

        return name
    }

    @SuppressLint("SetTextI18n")
    fun bind(transaction: TransactionEntity) {
        binding.tvName.text = getFormatedName(transaction.type)
        binding.tvTime.text = getFormatedDate(transaction.time)
        binding.tvAmount.text = "R$ " + transaction.amount.toString()
        binding.icon.setCompoundDrawablesWithIntrinsicBounds(getIconByTransactionType(transaction.type), 0, 0, 0)
        binding.root.setOnClickListener {
//            listener.onItemClick(transaction)
        }
    }
}