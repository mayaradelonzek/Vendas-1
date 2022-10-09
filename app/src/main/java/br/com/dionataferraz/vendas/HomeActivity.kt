package br.com.dionataferraz.vendas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dionataferraz.vendas.account.AccountActivity
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding
import br.com.dionataferraz.vendas.login.data.local.VendasDatabase
import br.com.dionataferraz.vendas.transaction.TransactionsActivity
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val databaseAcc: AccountDatabase by lazy {
        AccountDatabase.getInstance(context = App.context)
    }

    private val databaseUser: VendasDatabase by lazy {
        VendasDatabase.getInstance(context = App.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        setContentView(binding.root)
        configureActionBar()
        setViewValues()

        binding.newAcc.setOnClickListener {
            val intent  = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        binding.listTransac.setOnClickListener {
            val intent  = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }

        binding.tvAccountBalance.setOnClickListener {
            val intent  = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }
    }
    fun getAccValues(): AccountEntity {
        val account = databaseAcc.AccDao().getAccount()
        if (account.isEmpty()) {
            val newAcc = AccountEntity(
                id = 1,
                value = 0.0,
                date = Date()
            )
            databaseAcc.AccDao().insertAccount(newAcc)
        }
        return databaseAcc.AccDao().getAccount().get(0)
    }

    @SuppressLint("SetTextI18n")
    private fun setViewValues() {
        val account = getAccValues()
        val user = databaseUser.DAO().getUser().get(0)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_EVEN
        binding.tvAccountBalance.text = "R$ " + df.format(account.value)
        binding.tvLoggedUser.text = user.name
    }

    private fun configureActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

