package br.com.dionataferraz.vendas.transaction

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import br.com.dionataferraz.vendas.transaction.data.Transaction
import br.com.dionataferraz.vendas.TransactionAdapter
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class TransactionsActivity : AppCompatActivity(), TransactionAdapter.Listener {

    private lateinit var binding: ActivityTransactionsBinding
    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcList.adapter = adapter

        setupTransactionList()?.let { adapter.addList(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupTransactionList(): List<Transaction>? {
        val sharedPreferences = getSharedPreferences("transacListPrefs", Context.MODE_PRIVATE);
        var jsonTransactions = sharedPreferences.getString("transactionList", "");
        val jsonAdapter = getJsonTransactionListAdapter();

        if (jsonTransactions.isNullOrBlank()) {
            val listJson = jsonAdapter?.toJson(getTransactionList())
            sharedPreferences
                .edit()
                .putString("transactionList", listJson)
                .apply()

            jsonTransactions = sharedPreferences.getString("transactionList", "")
        }

        val listTransactionJson = jsonTransactions?.let { jsonAdapter?.fromJson(it) };

        return listTransactionJson
    }

    fun getJsonTransactionListAdapter(): JsonAdapter<List<Transaction>>? {
        val moshi = Moshi
            .Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(List::class.java, Transaction::class.java)

        return moshi.adapter(listType)
    }

    override fun onItemClick(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_LONG
        ).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTransactionList(): List<Transaction> {
        return listOf(
            Transaction(
                time = Date(2021, 12, 5, 10, 0),
                amount = 10.00,
                name = "Zaffari",
                type = TransactionType.MARKET
            ),
            Transaction(
                time = Date(2021, 12, 19, 5, 15),
                amount = 55.56,
                name = "Cinemark",
                type = TransactionType.LEISURE
            ),
            Transaction(
                time = Date(2021, 12, 5, 16, 50),
                amount = 130.66,
                name = "CEEE",
                type = TransactionType.BILL
            ),
            Transaction(
                time = Date(2021, 12, 5, 9, 43),
                amount = 39.76,
                name = "Netflix",
                type = TransactionType.BILL
            )
        )
    }
}