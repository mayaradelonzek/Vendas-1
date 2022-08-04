package br.com.dionataferraz.vendas

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var btOk: Button
    private lateinit var btClear: Button
    private lateinit var etName: EditText
    private lateinit var tvAnswer: TextView
    private lateinit var container: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        initializeBtOkListener()
        initializeBtClearListener()
    }

    private fun initializeViews() {
        btOk = findViewById(R.id.bt_ok)
        btClear = findViewById(R.id.bt_clear)
        etName = findViewById(R.id.et_name)
        tvAnswer = findViewById(R.id.tv_answer)
        container = findViewById(R.id.container)
    }

    private fun initializeBtOkListener() {
        btOk.setOnClickListener {
            if (!etName.text.isNullOrBlank()) {
                tvAnswer.text = getString(R.string.hello, etName.text)
                tvAnswer.visibility = View.VISIBLE
            } else {
                showMessage()
            }
        }
    }

    private fun initializeBtClearListener() {
        btClear.setOnClickListener {
            tvAnswer.text = ""
            etName.text.clear()
            tvAnswer.visibility = View.GONE
        }
    }

    private fun showMessage() {
        Snackbar.make(
            container,
            getString(R.string.empty_name),
            Snackbar.LENGTH_LONG
        ).setAction("Tente novamente") {
            Toast.makeText(
                this,
                "Sou um toast", Toast.LENGTH_LONG
            ).show()
        }.show()
    }
}