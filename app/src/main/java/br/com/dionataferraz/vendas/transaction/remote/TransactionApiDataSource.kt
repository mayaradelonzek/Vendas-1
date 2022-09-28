package br.com.dionataferraz.vendas.transaction.remote

import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.login.data.remote.RetrofitNetworkClient
import br.com.dionataferraz.vendas.login.data.request.NewUserRequest
import br.com.dionataferraz.vendas.login.data.response.UserResponse
import br.com.dionataferraz.vendas.transaction.data.TransactionRequest
import br.com.dionataferraz.vendas.transaction.data.TransactionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionApiDataSource {

    private val service = RetrofitNetworkClient
        .createNetworkClient()
        .create(TransactionApi::class.java)

    suspend fun findAllByIdUser(idUser: Int): Result<List<TransactionResponse>, ErrorModel> {
        return withContext(Dispatchers.IO) {
            try {
                val user = service.findAll(idUser.toString())
                Result.Success(user)
            } catch (exception:Exception){
                println(exception)
                println(exception.message)
                println(exception.cause)
                Result.Error(ErrorModel)
            }
        }
    }

    suspend fun register(idUser: Int, transactionRequest: TransactionRequest) {
        return withContext(Dispatchers.IO) {
            try {
                service.register(idUser.toString(), transactionRequest)
            } catch (exception:Exception){
                Result.Error(ErrorModel)
            }
        }
    }

    suspend fun delete(idTransaction: Int) {
        return withContext(Dispatchers.IO) {
            try {
                service.delete(idTransaction.toString())
            } catch (exception:Exception){
                Result.Error(ErrorModel)
            }
        }
    }

}