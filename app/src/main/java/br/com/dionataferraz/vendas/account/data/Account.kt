package br.com.dionataferraz.vendas.account.data

data class Account(
    val description: String,
    val value: Double,
    val responsible: String,
    val credit: Boolean,
    val debit: Boolean
)
