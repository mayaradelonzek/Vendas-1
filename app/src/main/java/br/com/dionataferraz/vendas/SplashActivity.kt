package br.com.dionataferraz.vendas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.login.LoginActivity
import br.com.dionataferraz.vendas.login.data.local.VendasDatabase

class SplashActivity : AppCompatActivity() {

    private val database: VendasDatabase by lazy {
        VendasDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val user = database.DAO().getUser()
            var intent = Intent(this, LoginActivity::class.java)

            if (!user.isEmpty()) intent = Intent(this, HomeActivity::class.java)
            else  intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }, 3000)
    }

}