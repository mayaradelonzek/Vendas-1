package br.com.dionataferraz.vendas.transaction.usecase

import br.com.dionataferraz.vendas.account.usecase.updateAccountUseCase
import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.login.domain.usecase.GetUserUsecase
import br.com.dionataferraz.vendas.transaction.data.TransactionResponse
import br.com.dionataferraz.vendas.transaction.repository.TransactionRepository

class DeleteTransactionUseCase() {

    private val repository by lazy {
        TransactionRepository()
    }

    suspend fun delete(idTransaction: Int) {
        repository.delete(idTransaction)
    }

}