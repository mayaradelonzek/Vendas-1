package br.com.dionataferraz.vendas

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityLoginBinding
import br.com.dionataferraz.vendas.databinding.ActivityProfileBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityProfileBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        setContentView(binding.root)

        binding.btSave.setOnClickListener {
            Toast.makeText(
                this,
                binding.etName.text.toString() + "'s profile created",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun configureActionBar(){

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    data class Person(
        val name: String,
        val age: Int
    )

    private fun saveAndFetchPreferences() {
        val sharedPreferences = getSharedPreferences(
            "Profile",
            MODE_PRIVATE
        )
        val edit = sharedPreferences.edit()

        edit.putString("name", "Dionata")
        edit.putInt("age", 28)
        edit.apply()

//        findViewById<TextView>(R.id.tv_name).apply {
//            val name = sharedPreferences.getString("name", null)
//            val age = sharedPreferences.getInt("age", 0)
//            if (!name.isNullOrEmpty() && age > 0) {
//                text = "$name $age"
//            }
//        }
    }
}