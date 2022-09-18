package br.com.dionataferraz.vendas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dionataferraz.vendas.account.AccountActivity
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        setContentView(binding.root)
        configureActionBar()

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.newAcc.setOnClickListener {
//            viewModel.login(null, null)
            val intent  = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

