package br.com.dionataferraz.vendas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences = getSharedPreferences(
            "Profile",
            MODE_PRIVATE
        )
        val edit = sharedPreferences.edit()
        val person = Person(
            name = "Dionata",
            age = 29
        )
        val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val adapter = moshi.adapter(Person::class.java)
        val personString = adapter.toJson(person)
        edit.putString("person", personString)
        edit.apply()

        findViewById<TextView>(R.id.tv_name).apply {
            val personFromSharedPreferences = sharedPreferences.getString("person", null)
            val personFromAdapter = adapter.fromJson(
                personFromSharedPreferences
            )

            if (personFromAdapter != null) {
                text = "${personFromAdapter.name} ${personFromAdapter.age}"
            }
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

        findViewById<TextView>(R.id.tv_name).apply {
            val name = sharedPreferences.getString("name", null)
            val age = sharedPreferences.getInt("age", 0)
            if (!name.isNullOrEmpty() && age > 0) {
                text = "$name $age"
            }
        }
    }
}