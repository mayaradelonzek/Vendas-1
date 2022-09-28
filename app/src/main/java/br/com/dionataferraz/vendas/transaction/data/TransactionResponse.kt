package br.com.dionataferraz.vendas.transaction.data

import br.com.dionataferraz.vendas.transaction.TransactionType
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.util.Date

@JsonClass(generateAdapter = true)
class TransactionResponse(
    val transactionDate: Date,
    val value: Double,
    val description: String,
    val transactionType: TransactionType
) {
    var id:Int = 0
}