package br.com.dionataferraz.vendas.transaction.usecase

import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.login.domain.usecase.GetUserUsecase
import br.com.dionataferraz.vendas.transaction.data.TransactionResponse
import br.com.dionataferraz.vendas.transaction.repository.TransactionRepository

class FindAllTransactionsUseCase {

    private val repository by lazy {
        TransactionRepository()
    }

    suspend fun findAll(): Result<List<TransactionResponse>, ErrorModel> {
        val loggedUser = GetUserUsecase().getLoggedUser()
        return repository.findAll(loggedUser.id)
    }

}