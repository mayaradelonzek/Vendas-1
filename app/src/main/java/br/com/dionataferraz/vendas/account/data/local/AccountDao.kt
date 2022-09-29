package br.com.dionataferraz.vendas.account.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccountDao {

    @Update
    fun updateAccount(accountEntity: AccountEntity)

    @Insert
    fun insertAccount(accountEntity: AccountEntity)

    @Query("SELECT * from accountTable")
    fun getAccount(): List<AccountEntity>

    @Query("SELECT * from accountTable WHERE id = :id")
    fun getAccount(id: Int): AccountEntity

}