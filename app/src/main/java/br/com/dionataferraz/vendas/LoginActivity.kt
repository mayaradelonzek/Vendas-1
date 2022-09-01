package br.com.dionataferraz.vendas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityLoginBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = LoginViewModel()

        val view = binding.root
        setContentView(view)

        binding.btLogin.setOnClickListener {
            viewModel.login(null, null)
            val intent  = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

            val sharedPreferences = getSharedPreferences(
                "Profile",
                MODE_PRIVATE
            )
            val edit = sharedPreferences.edit()
            val person = ProfileActivity.Person(
                name = "Dionata Leonel",
                age = 29
            )
            val moshi = Moshi
                .Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val adapter = moshi.adapter(ProfileActivity.Person::class.java)
            val personString = adapter.toJson(person)
            edit.putString("person", personString)
            edit.apply()
        }

        viewModel.shouldShowError.observe(this) { shouldShow ->
            if (shouldShow){
                Toast.makeText(
                    this,
                    "Deu ruim",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}