package br.com.dionataferraz.vendas.account

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dionataferraz.vendas.HomeActivity
import br.com.dionataferraz.vendas.account.data.Account
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class AccountActivity : AppCompatActivity() {

    private lateinit var viewModel: AccountViewModel
    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityAccountBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        configureActionBar()
        setContentView(binding.root)
        viewModel = AccountViewModel()

        binding.btDeposit.setOnClickListener {
            val value =
                with(binding.valueET.text.toString()) { if (this.isBlank()) 0.0 else this.toDouble() }

            viewModel.depositAcc(1, value)
        }

        binding.btRedeem.setOnClickListener {
            val value =
                with(binding.valueET.text.toString()) { if (this.isBlank()) 0.0 else this.toDouble() }

            viewModel.withdrawAcc(1, value)
        }

        viewModel.success.observe(this) { valorRetirada ->
            Toast.makeText(
                this,
                "saldo atualizado com sucesso",
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        viewModel.error.observe(this) { msg ->
            Toast.makeText(
                this,
                msg,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun configureWithdraw() {

    }

    fun getAccAdapter(): JsonAdapter<Account>? {
        val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        return moshi.adapter(Account::class.java);
    }

    private fun configureActionBar(){
        setSupportActionBar(binding.createAccToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

fun toastConfig(message: String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}

