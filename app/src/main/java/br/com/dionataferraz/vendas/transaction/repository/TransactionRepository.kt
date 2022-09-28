package br.com.dionataferraz.vendas.transaction.repository

import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.transaction.data.TransactionRequest
import br.com.dionataferraz.vendas.transaction.data.TransactionResponse
import br.com.dionataferraz.vendas.transaction.remote.TransactionApiDataSource

class TransactionRepository {

    private val remoteDataSource by lazy {
        TransactionApiDataSource()
    }

    suspend fun findAll(idUser: Int): Result<List<TransactionResponse>, ErrorModel> {
        return remoteDataSource.findAllByIdUser(idUser)
    }

    suspend fun register(idUser: Int, transactionRequest: TransactionRequest) {
        return remoteDataSource.register(idUser, transactionRequest)
    }

    suspend fun delete(idTransaction: Int) {
        return remoteDataSource.delete(idTransaction)
    }

}