package br.com.dionataferraz.vendas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        binding.newAcc.setOnClickListener {
//            viewModel.login(null, null)
            val intent  = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

    }
}