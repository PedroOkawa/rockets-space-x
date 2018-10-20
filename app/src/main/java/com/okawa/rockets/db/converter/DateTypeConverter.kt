package com.okawa.rockets.db.converter

import android.arch.persistence.room.TypeConverter
import java.util.*


object DateTypeConverter {

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    @JvmStatic
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}