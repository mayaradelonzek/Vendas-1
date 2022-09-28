package br.com.dionataferraz.vendas.account.usecase

import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity
import br.com.dionataferraz.vendas.login.domain.usecase.GetUserUsecase
import br.com.dionataferraz.vendas.transaction.TransactionType
import br.com.dionataferraz.vendas.transaction.data.TransactionRequest
import br.com.dionataferraz.vendas.transaction.usecase.InsertTransactionUsecase
import kotlinx.coroutines.launch
import java.util.*

class updateAccountUseCase(private val acc: AccountEntity,
                           private val amount: Double) {

    private val database: AccountDatabase by lazy {
        AccountDatabase.getInstance(context = App.context)
    }

    private fun invoke() {
        database.AccDao().updateAccount(acc)
    }

    fun withdraw() {
        acc.value -= amount
        invoke()
    }

    fun deposit() {
        acc.value += amount
        invoke()
    }

}