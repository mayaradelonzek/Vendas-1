package br.com.dionataferraz.vendas.transaction.data

import br.com.dionataferraz.vendas.transaction.TransactionType

class TransactionRequest(
    private val value: Double,
    private val description: String,
    private val transactionType: TransactionType
)