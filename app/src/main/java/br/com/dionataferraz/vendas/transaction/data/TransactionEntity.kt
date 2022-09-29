package br.com.dionataferraz.vendas.transaction.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.dionataferraz.vendas.account.data.local.Operation
import com.squareup.moshi.JsonClass
import java.util.Date

@Entity(tableName = "transactionTable")
@JsonClass(generateAdapter = true)
class TransactionEntity(
    val time: Date,
    val amount: Double,
    val name: String,
    val type: Operation
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}