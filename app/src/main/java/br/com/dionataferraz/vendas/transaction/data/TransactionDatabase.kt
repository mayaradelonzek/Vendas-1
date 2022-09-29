package br.com.dionataferraz.vendas.transaction.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.dionataferraz.vendas.account.converters.Converters

@Database(entities = [TransactionEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun DAO(): TransactionDao

    companion object {
        fun getInstance(context: Context): TransactionDatabase {
            return Room.databaseBuilder(
                context,
                TransactionDatabase::class.java,
                "transaction.db"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}