package br.com.dionataferraz.vendas

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.databinding.ItemListBinding
import br.com.dionataferraz.vendas.transaction.TransactionType
import br.com.dionataferraz.vendas.transaction.local.TransactionEntity
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
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
        clear()
        notifyItemRangeRemoved(0, listItem.size)
        listItem.addAll(list)
    }

    fun addList(list: List<TransactionEntity>) {
        listItem.clear()
        listItem.addAll(list)
    }

    fun updateItem(item: TransactionEntity, position: Int) {
        listItem[position] = item
        notifyItemChanged(position)
    }

    fun getItemByPosition(position: Int): TransactionEntity {
        return listItem[position]
    }

    fun clear() {
        listItem.clear()
        notifyDataSetChanged()
    }

}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
): RecyclerView.ViewHolder(binding.root) {

    fun getIconByTransactionType(type: TransactionType): Int {
        var icon = R.drawable.ic_baseline_arrow_drop_down_24

        when {
            TransactionType.MARKET.equals(type) -> icon = R.drawable.ic_baseline_shopping_cart_24
            TransactionType.GAS_STATION.equals(type) -> icon = R.drawable.ic_baseline_local_gas_station_24
            TransactionType.PUB.equals(type) -> icon = R.drawable.ic_baseline_local_bar_24
        }

        return icon
    }

    @SuppressLint("SimpleDateFormat")
    fun getFormatedDate(date: Date): String {
        val loc = Locale("pt", "BR")
        return SimpleDateFormat("dd MMM yyyy HH:mm", loc).format(date)
    }

    fun getFormatedName(operation: TransactionType): String {
        var name = ""

        when {
            TransactionType.PUB.equals(operation) -> name = "Pub"
            TransactionType.GAS_STATION.equals(operation) -> name = "Combustível"
            TransactionType.MARKET.equals(operation) -> name = "Supermercado"
        }

        return name
    }

    @SuppressLint("SetTextI18n")
    fun bind(transaction: TransactionEntity) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_EVEN

        binding.tvName.text = getFormatedName(transaction.transactionType)
        binding.tvTime.text = getFormatedDate(transaction.time)
        binding.tvAmount.text = "R$ " + df.format(transaction.amount)
        binding.icon.setCompoundDrawablesWithIntrinsicBounds(getIconByTransactionType(transaction.transactionType), 0, 0, 0)
        binding.root.setOnClickListener {
//            listener.onItemClick(transaction)
        }
    }
}