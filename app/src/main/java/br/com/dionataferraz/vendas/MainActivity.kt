package br.com.dionataferraz.vendas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btOk: Button
    private lateinit var btClear: Button
    private lateinit var etName: EditText
    private lateinit var tvAnswer: TextView

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
    }

    private fun initializeBtOkListener() {
        btOk.setOnClickListener {
            if (!etName.text.isNullOrBlank()) {
                tvAnswer.text = getString(R.string.hello, etName.text)
                tvAnswer.visibility = View.VISIBLE
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
}