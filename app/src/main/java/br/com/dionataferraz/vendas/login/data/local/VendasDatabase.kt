package br.com.dionataferraz.vendas.login.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.dionataferraz.vendas.account.converters.Converters

@Database(entities = [UserEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class VendasDatabase : RoomDatabase() {

    abstract fun DAO(): UserDao

    companion object {
        fun getInstance(context: Context): VendasDatabase {
            return Room.databaseBuilder(
                context,
                VendasDatabase::class.java,
                "vendas.db"
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        }
    }
}