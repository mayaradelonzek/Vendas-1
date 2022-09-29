package br.com.dionataferraz.vendas.account.converters

import androidx.room.TypeConverter
import br.com.dionataferraz.vendas.account.data.local.Operation
import java.util.Date

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
    fun toTypeEnum(string: String?): Operation? {
        return string?.let { Operation.valueOf(it) }
    }

    @TypeConverter
    fun fromTypeEnum(type: Operation?): String {
        if (type == null) return Operation.DEPOSIT.name

        return type.name
    }

}