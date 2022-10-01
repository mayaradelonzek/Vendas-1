package br.com.dionataferraz.vendas.transaction.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * from transactionTable order by 'desc'")
    fun getTransactions(): List<TransactionEntity>

}