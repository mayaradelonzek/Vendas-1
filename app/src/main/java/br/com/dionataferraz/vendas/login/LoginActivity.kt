package br.com.dionataferraz.vendas.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.HomeActivity
import br.com.dionataferraz.vendas.databinding.ActivityLoginBinding
import br.com.dionataferraz.vendas.login.data.local.UserEntity
import br.com.dionataferraz.vendas.login.data.local.VendasDatabase
import br.com.dionataferraz.vendas.profile.ProfileActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    private val database: VendasDatabase by lazy {
        VendasDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = LoginViewModel()

        val view = binding.root
        setContentView(view)

        binding.btLogin.setOnClickListener {
            getProgressBar().bringToFront();
            getProgressBar().visibility = View.VISIBLE
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
            )
        }

        binding.register.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        viewModel.shouldShowHome.observe(this) { userResponse ->
            if (userResponse != null) {
                val user = database.DAO().getUser()
                if(user.isEmpty()) {
                    database.DAO().insertUser(
                        UserEntity(
                            id = userResponse.id,
                            name = userResponse.name,
                            email = userResponse.email,
                            password = userResponse.password,
                        )
                    )
                }
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            getProgressBar().visibility = View.INVISIBLE
        }

        viewModel.shouldShowError.observe(this) { shouldShow ->
            if (shouldShow) {
                Toast.makeText(
                    this,
                    "Usuário e/ou senha incorreto(s)",
                    Toast.LENGTH_LONG
                ).show()
            }
            getProgressBar().visibility = View.INVISIBLE
        }
    }

    fun getProgressBar(): ProgressBar {
        return binding.progressBar
    }
}