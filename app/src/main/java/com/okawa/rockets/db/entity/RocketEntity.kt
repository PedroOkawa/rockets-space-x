package com.okawa.rockets.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rocket")
data class RocketEntity(@PrimaryKey @ColumnInfo(name = "rocket_id") var rocketId: String,
                         @ColumnInfo(name = "name") var name: String,
                         @ColumnInfo(name = "description") var description: String,
                         @ColumnInfo(name = "country") var country: String,
                         @ColumnInfo(name = "active") var active: Boolean,
                         @ColumnInfo(name = "engines_count") var enginesCount: Int,
                         @ColumnInfo(name = "images") var images: List<String>)