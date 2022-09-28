package br.com.dionataferraz.vendas.login.data.remote

import br.com.dionataferraz.vendas.login.data.request.NewUserRequest
import br.com.dionataferraz.vendas.login.data.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    @GET("api/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String,
    ): UserResponse

    @POST("api/login")
    suspend fun register(
        @Body newUserRequest: NewUserRequest
    ): UserResponse

}