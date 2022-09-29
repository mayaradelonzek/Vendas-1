package br.com.dionataferraz.vendas.login.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserEntity(
    val name: String,
    val email: String,
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}