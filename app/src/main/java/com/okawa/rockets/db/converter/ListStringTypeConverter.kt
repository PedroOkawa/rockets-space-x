package com.okawa.rockets.db.converter

import android.arch.persistence.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*

object ListStringTypeConverter {

    private val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    private val stringListAdapter: JsonAdapter<List<String>> by lazy {
        val stringListType = Types.newParameterizedType(List::class.java, String::class.java)
        moshi.adapter<List<String>>(stringListType)
    }

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): List<String>? {
        return if (value == null) Collections.emptyList() else stringListAdapter.fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toString(list: List<String>): String? {
        return stringListAdapter.toJson(list)
    }
}