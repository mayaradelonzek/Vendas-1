package br.com.dionataferraz.vendas.account.data.remote

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity
import br.com.dionataferraz.vendas.account.data.local.Operation
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountDataSource : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding

    private val database: AccountDatabase by lazy {
        AccountDatabase.getInstance(context = App.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val account = database.AccDao().getAccount(1)
            Log.e("account", account.toString())
        }
    }

}

object ErrorModel
sealed class Result<out S, out E> {
    data class Success<S>(val value: S) : Result<S, Nothing>()
    data class Error<E>(val value: E) : Result<Nothing, E>()

    fun get(): S? {
        return when (this) {
            is Success -> value
            else -> null
        }
    }
}