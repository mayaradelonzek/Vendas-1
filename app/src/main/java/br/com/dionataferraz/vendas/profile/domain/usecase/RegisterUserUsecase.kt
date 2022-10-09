package br.com.dionataferraz.vendas.profile.domain.usecase

import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.login.data.repository.LoginRepository
import br.com.dionataferraz.vendas.login.data.request.NewUserRequest
import br.com.dionataferraz.vendas.login.data.response.UserResponse

class RegisterUserUsecase {
    private val repository by lazy {
        LoginRepository()
    }

    suspend fun regster(newUserRequest: NewUserRequest): Result<UserResponse, ErrorModel> {
        return repository.register(newUserRequest)
    }
}