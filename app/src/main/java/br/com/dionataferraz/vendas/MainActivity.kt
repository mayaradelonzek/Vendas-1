package br.com.dionataferraz.vendas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeBtOkListener()
        initializeBtClearListener()
    }

    private fun initializeBtOkListener() {
        binding.run {
            btOk.setOnClickListener {
                if (!etName.text.isNullOrBlank()) {
                    tvAnswer.text = getString(R.string.hello, etName.text)
                    tvAnswer.visibility = View.VISIBLE
                } else {
                    showMessage()
                }
            }
        }
    }

    private fun initializeBtClearListener() {
        binding.run {
            btClear.setOnClickListener {
                tvAnswer.text = ""
                etName.text.clear()
                tvAnswer.visibility = View.GONE
            }
        }
    }

    private fun showMessage() {
        binding.run {
            Snackbar.make(
                container,
                getString(R.string.empty_name),
                Snackbar.LENGTH_LONG
            ).setAction("Tente novamente") {
                Toast.makeText(
                    applicationContext,
                    "Sou um toast", Toast.LENGTH_LONG
                ).show()
            }.show()
        }
    }
}