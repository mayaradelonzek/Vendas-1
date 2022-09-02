package br.com.dionataferraz.vendas

import android.os.Bundle
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
        binding = ActivityProfileBinding.inflate(layoutInflater)

        binding.btSave.setOnClickListener {
            Toast.makeText(this,  binding.rg.checkedRadioButtonId.toString(), Toast.LENGTH_LONG).show()
        }
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

        /*findViewById<TextView>(R.id.tv_name).apply {
            val name = sharedPreferences.getString("name", null)
            val age = sharedPreferences.getInt("age", 0)
            if (!name.isNullOrEmpty() && age > 0) {
                text = "$name $age"
            }
        }*/
    }
}