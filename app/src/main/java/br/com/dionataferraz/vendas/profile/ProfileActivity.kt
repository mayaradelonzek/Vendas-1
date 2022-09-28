package br.com.dionataferraz.vendas.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.HomeActivity
import br.com.dionataferraz.vendas.databinding.ActivityProfileBinding
import br.com.dionataferraz.vendas.login.LoginActivity
import br.com.dionataferraz.vendas.login.LoginViewModel
import br.com.dionataferraz.vendas.login.data.remote.LoginDataSource
import br.com.dionataferraz.vendas.login.data.request.NewUserRequest

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityProfileBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        setContentView(binding.root)
        viewModel = ProfileViewModel()

        binding.btSave.setOnClickListener {
            val newUser = NewUserRequest(
                name = binding.etName.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString(),
            )
            viewModel.register(newUser)
        }

        viewModel.shouldShowLogin.observe(this) { shouldShow ->
            if (shouldShow) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this,
                    binding.etName.text.toString() + "'s profile created",
                    Toast.LENGTH_LONG).show()
            }
        }

        viewModel.shouldShowError.observe(this) { shouldShow ->
            if (shouldShow) {
                Toast.makeText(
                    this,
                    "preencha os campos corretamente",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun configureActionBar(){

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}