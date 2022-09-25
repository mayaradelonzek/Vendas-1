package br.com.dionataferraz.vendas.account.data

import br.com.dionataferraz.vendas.account.data.local.Operation
import java.util.*

data class Account(
    val id: Int,
    val value: Double,
    val date: Date,
    val type: Operation
)

