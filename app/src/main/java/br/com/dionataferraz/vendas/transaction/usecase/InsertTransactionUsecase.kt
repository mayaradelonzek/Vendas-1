package br.com.dionataferraz.vendas.transaction.usecase

import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.login.domain.usecase.GetUserUsecase
import br.com.dionataferraz.vendas.transaction.data.TransactionRequest
import br.com.dionataferraz.vendas.transaction.data.TransactionResponse
import br.com.dionataferraz.vendas.transaction.repository.TransactionRepository

class InsertTransactionUsecase(private val transaction: TransactionRequest) {

    private val repository by lazy {
        TransactionRepository()
    }

    suspend fun register() {
        val loggedUser = GetUserUsecase().getLoggedUser()
        repository.register(loggedUser.id, transaction)
    }

}
