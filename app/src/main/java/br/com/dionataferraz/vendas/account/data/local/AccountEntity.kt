package br.com.dionataferraz.vendas.account.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "accountTable")
data class AccountEntity (

    @PrimaryKey val id: Int,
    var value: Double,
    val date: Date,
    val type: Operation

)
