package br.com.dionataferraz.vendas.login.data.response

data class UserResponse (
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)