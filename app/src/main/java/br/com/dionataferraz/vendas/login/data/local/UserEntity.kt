package br.com.dionataferraz.vendas.login.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
class UserEntity (
    val name: String,
    @PrimaryKey
    val email: String,
    val password: String
)

