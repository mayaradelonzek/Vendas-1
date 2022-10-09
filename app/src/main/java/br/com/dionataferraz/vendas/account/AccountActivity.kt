package br.com.dionataferraz.vendas.account

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.HomeActivity
import br.com.dionataferraz.vendas.account.data.Account
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding
import br.com.dionataferraz.vendas.transaction.TransactionType
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
        configureSpinnerTypes()
        viewModel = AccountViewModel()

        binding.novaCompraBtnComprar.setOnClickListener {
            getProgressBar().visibility = View.VISIBLE
            val value =
                with(binding.valueET.text.toString()) { if (this.isBlank()) 0.0 else this.toDouble() }
            val spinnerTypeValue = getSpinnerType().selectedItem.toString()

            viewModel.registerItemBought(1, value, TransactionType.getEnumByValue(spinnerTypeValue))
        }

        viewModel.success.observe(this) { valorRetirada ->
            Toast.makeText(
                this,
                "Compra realizada",
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            getProgressBar().visibility = View.INVISIBLE
        }

        viewModel.error.observe(this) { msg ->
            Toast.makeText(
                this,
                msg,
                Toast.LENGTH_LONG
            ).show()
            getProgressBar().visibility = View.INVISIBLE
        }
    }

    fun getSpinnerType(): Spinner {
        return this.binding.novaCompraTiposSpinner;
    }

    fun configureSpinnerTypes() {
        val spinner: Spinner = getSpinnerType()
        spinner.setAdapter(ArrayAdapter<TransactionType>(this,
            android.R.layout.simple_spinner_item,
            TransactionType.values()))
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

    fun getProgressBar(): ProgressBar {
        return binding.progressBar
    }
}


fun toastConfig(message: String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}

