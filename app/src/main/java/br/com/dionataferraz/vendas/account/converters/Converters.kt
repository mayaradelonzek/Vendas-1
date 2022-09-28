package br.com.dionataferraz.vendas.account.converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import br.com.dionataferraz.vendas.transaction.TransactionType
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoField
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toTypeEnum(string: String?): TransactionType? {
        return string?.let { TransactionType.valueOf(it) }
    }

    @TypeConverter
    fun fromTypeEnum(type: TransactionType?): String {
        if (type == null) return TransactionType.GAS_STATION.name

        return type.name
    }

}