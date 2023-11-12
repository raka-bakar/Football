package com.data.persist

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import java.time.ZonedDateTime

@ProvidedTypeConverter
class Converter(private val moshi: Moshi) {
    @TypeConverter
    fun fromStringToZonedDateTime(value: String?): ZonedDateTime? {
        if (value == null) {
            return null
        }
        val adapter = moshi.adapter(ZonedDateTime::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromZonedDateTimeToString(dateTime: ZonedDateTime?): String? {
        if (dateTime == null) {
            return null
        }
        val adapter = moshi.adapter(ZonedDateTime::class.java)
        return adapter.toJson(dateTime)
    }
}