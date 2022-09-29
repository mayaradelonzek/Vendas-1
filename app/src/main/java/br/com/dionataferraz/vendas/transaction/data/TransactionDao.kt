package br.com.dionataferraz.vendas.transaction.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * from transactionTable")
    fun getTransactions(): List<TransactionEntity>
}