package br.com.dionataferraz.vendas

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
class Transaction(
    val time: Date,
    val amount: Double,
    val name: String,
    val type: TransactionType) {

}