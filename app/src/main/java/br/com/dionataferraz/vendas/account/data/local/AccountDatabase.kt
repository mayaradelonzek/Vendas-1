package br.com.dionataferraz.vendas.account.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.dionataferraz.vendas.account.converters.Converters

@Database(entities = [AccountEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AccountDatabase: RoomDatabase() {

    abstract fun AccDao(): AccountDao

    companion object {
        fun getInstance(context: Context): AccountDatabase {
            return Room.databaseBuilder(
                context,
                AccountDatabase::class.java,
                "accounts.db"
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        }
    }
}