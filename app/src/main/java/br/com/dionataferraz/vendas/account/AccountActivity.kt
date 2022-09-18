package br.com.dionataferraz.vendas.account

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dionataferraz.vendas.HomeActivity
import br.com.dionataferraz.vendas.R
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
        setupAccPref()

        binding.btSave.setOnClickListener {
            val description = binding.descriptionET.text.toString()
            val value = with(binding.valueET.text.toString()) { if (this.isBlank()) 0.0 else this.toDouble() }
            val responsible = binding.responsibleET.text.toString()
            val credit = binding.creditAcc.isChecked
            val debit = binding.debitAcc.isChecked

            createAcc(description, value , responsible, credit, debit)
        }

        binding.btClear.setOnClickListener {
            val sharedPreferences = getSharedPreferences("accPrefs", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit();

            toastConfig("Informações resetadas com sucesso!", this)
            val intent  = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun getAccAdapter(): JsonAdapter<Account>? {
        val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        return moshi.adapter(Account::class.java);
    }

    fun createAcc(description: String, value: Double, responsible: String, credit: Boolean, debit: Boolean) {
        viewModel.validateAcc(description, value, responsible, credit, debit);
        val sharedPreferences = getSharedPreferences("accPrefs", Context.MODE_PRIVATE);
        val adapter = getAccAdapter();

        viewModel.account.observe(this) { acc ->
            val save = adapter?.toJson(acc)
            sharedPreferences
                .edit()
                .putString(getString(R.string.accSaved), save)
                .apply()

            toastConfig("Conta criada com sucesso!", this)

            val intent  = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        viewModel.error.observe(this) { msgErr ->
            toastConfig(msgErr, this)
        }
    }

    private fun setupAccPref() {
        val sharedPreferences = getSharedPreferences("accPrefs", MODE_PRIVATE);
        val accPrefsJson = sharedPreferences.getString(getString(R.string.accSaved), "");

        if (!accPrefsJson.isNullOrBlank()) {
            val adapter = getAccAdapter();
            val acc = adapter?.fromJson(accPrefsJson);

            binding.descriptionET.setText(acc?.description)
            binding.valueET.setText(acc?.value.toString())
            binding.responsibleET.setText(acc?.responsible)
            acc?.credit?.let { binding.creditAcc.setChecked(it) }
            acc?.debit?.let { binding.debitAcc.setChecked(it) }
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