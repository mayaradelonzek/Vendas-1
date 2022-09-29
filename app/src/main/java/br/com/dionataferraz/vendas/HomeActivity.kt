package br.com.dionataferraz.vendas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dionataferraz.vendas.account.AccountActivity
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity
import br.com.dionataferraz.vendas.account.data.local.Operation
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding
import br.com.dionataferraz.vendas.transaction.TransactionsActivity
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val databaseAcc: AccountDatabase by lazy {
        AccountDatabase.getInstance(context = App.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        setContentView(binding.root)
        configureActionBar()
        configureAcc()
        setSaldo()

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.newAcc.setOnClickListener {
//            viewModel.login(null, null)
            val intent  = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        binding.listTransac.setOnClickListener {
//            viewModel.login(null, null)
            val intent  = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }

        binding.tvAccountBalance.setOnClickListener {
            val intent  = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setSaldo() {
        val account = databaseAcc.AccDao().getAccount(1)
        binding.tvAccountBalance.text = "R$ " + account.value.toString()
    }

    private fun configureAcc() {
        val account = databaseAcc.AccDao().getAccount(1)

        if (account == null) {
            val dao = databaseAcc.AccDao()
            val acc = AccountEntity(
                id = 1,
                value = 300.00,
                date = Date(2021, 12, 5, 10, 0),
                type = Operation.DEPOSIT
            )

            dao.insertAccount(acc)
        }

    }

    private fun configureActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

