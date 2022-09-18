package br.com.dionataferraz.vendas.login.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class VendasDatabase : RoomDatabase() {

    abstract fun DAO() : UserDao

    companion object {
        fun getInstance(context: Context) : VendasDatabase {
            return Room.databaseBuilder(
                context,
                VendasDatabase::class.java,
                "vendas.db"
            ).build()
        }
    }
}