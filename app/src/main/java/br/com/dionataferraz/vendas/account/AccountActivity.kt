package br.com.dionataferraz.vendas.account

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import br.com.dionataferraz.vendas.HomeActivity
import br.com.dionataferraz.vendas.R
import br.com.dionataferraz.vendas.account.data.Account
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.math.pow

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

        binding.btSave.setOnClickListener {
            val description = binding.descriptionET.text.toString()
            val value = binding.valueET.text.toString().toDouble().pow(2)
            val responsible = binding.responsibleET.text.toString()
            val credit = binding.creditAcc.isChecked
            val debit = binding.debitAcc.isChecked
            createAcc(description, value, responsible, credit, debit)
            toastConfig("Conta criada com sucesso!", this)

            val intent  = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun createAcc(description: String, value: Double, responsible: String, credit: Boolean, debit: Boolean) {
        val sharedPreferences = getSharedPreferences("accPreferences", MODE_PRIVATE);
        val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val adapter = moshi
            .adapter(Account::class.java)

        viewModel.accountLiveData.observe(this) { acc ->
            val save = adapter.toJson(acc)
            sharedPreferences
                .edit()
                .putString("Account", save)
                .apply()
        }
    }

    fun toastConfig(message: String, context: Context) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun configureActionBar(){
        setSupportActionBar(binding.createAccToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}