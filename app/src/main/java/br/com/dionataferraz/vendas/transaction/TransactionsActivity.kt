package br.com.dionataferraz.vendas.transaction

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.TransactionAdapter
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding
import br.com.dionataferraz.vendas.transaction.local.TransactionEntity
import kotlin.streams.toList


class TransactionsActivity : AppCompatActivity(), TransactionAdapter.Listener {

    private lateinit var binding: ActivityTransactionsBinding
    private lateinit var viewModel: TransactionViewModel
    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureActionBar()

        viewModel = TransactionViewModel()
        viewModel.updateTrasactionView()

        val itemTouchHelper = ItemTouchHelper(getSwipe())
        itemTouchHelper.attachToRecyclerView(binding.rcList)

        viewModel.transactionList.observe(this) { transactions ->
            if (transactions != null && !transactions.isEmpty()) {
                val transactionsE = transactions.stream().map {
                    TransactionEntity(
                        id = it.id,
                        amount = it.value,
                        name = it.description,
                        time = it.transactionDate,
                        transactionType = it.transactionType
                    )
                }.toList()
                binding.rcList.adapter = adapter
                adapter.addList(transactionsE)
            } else {
                adapter.clear()
            }
        }
    }

    fun getSwipe(): ItemTouchHelper.SimpleCallback {
        return object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Toast.makeText(this@TransactionsActivity, "on Move", Toast.LENGTH_SHORT).show()
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                //Remove swiped item from list and notify the RecyclerView
                val position = viewHolder.adapterPosition
//                arrayList.remove(position)
                val item = adapter.getItemByPosition(position = position)
//                Toast.makeText(this@TransactionsActivity, item.amount.toString(), Toast.LENGTH_SHORT).show()
                viewModel.delete(item.id)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onItemClick(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun getTransactionList(): List<TransactionEntity> {
        return databaseTransac.DAO().getTransactions()
    }

    private fun configureActionBar(){
        setSupportActionBar(binding.transactionstoolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}