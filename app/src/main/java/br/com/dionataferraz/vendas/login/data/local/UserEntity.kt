package br.com.dionataferraz.vendas.login.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserEntity(
    @PrimaryKey var id: Int,
    val name: String,
    val email: String,
    val password: String
)