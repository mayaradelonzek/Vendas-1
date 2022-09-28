package br.com.dionataferraz.vendas.transaction

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.TransactionAdapter
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding
import br.com.dionataferraz.vendas.transaction.data.TransactionDatabase
import br.com.dionataferraz.vendas.transaction.data.TransactionEntity
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

    private val databaseTransac: TransactionDatabase by lazy {
        TransactionDatabase.getInstance(context = App.context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureActionBar()

        binding.rcList.adapter = adapter

        adapter.addList(getTransactionList())
//        setupTransactionList()?.let { adapter.addList(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupTransactionList(): List<TransactionEntity>? {
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

    fun getJsonTransactionListAdapter(): JsonAdapter<List<TransactionEntity>>? {
        val moshi = Moshi
            .Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(List::class.java, TransactionEntity::class.java)

        return moshi.adapter(listType)
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