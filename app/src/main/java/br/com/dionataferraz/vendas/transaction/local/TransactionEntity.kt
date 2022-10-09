package br.com.dionataferraz.vendas.transaction.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.dionataferraz.vendas.transaction.TransactionType
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "transactionTable")
@JsonClass(generateAdapter = true)
class TransactionEntity(
    val time: Date,
    val amount: Double,
    val name: String,
    val transactionType: TransactionType
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(
        id: Int,
        time: Date,
        amount: Double,
        name: String,
        transactionType: TransactionType
    ) : this(time, amount, name, transactionType) {
        this.id = id
    }

}