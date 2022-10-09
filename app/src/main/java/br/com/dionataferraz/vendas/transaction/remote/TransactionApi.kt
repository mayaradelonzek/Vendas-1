package br.com.dionataferraz.vendas.transaction.remote

import br.com.dionataferraz.vendas.transaction.data.TransactionRequest
import br.com.dionataferraz.vendas.transaction.data.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionApi {

    @POST("api/transaction/{idUser}")
    suspend fun register(
        @Path("idUser") idUser: String,
        @Body transactionRequest: TransactionRequest
    )

    @GET("api/transaction/{idUser}")
    suspend fun findAll(
        @Path("idUser") idUser: String
    ): List<TransactionResponse>

    @DELETE("api/transaction/{idTransaction}")
    suspend fun delete(
        @Path("idTransaction") idTransaction: String
    )

}