package com.okawa.rockets.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rocket")
data class RocketEntity(@PrimaryKey @ColumnInfo(name = "id") var id: Long = 0,
                         @ColumnInfo(name = "rocket_id") var rocketId: String,
                         @ColumnInfo(name = "rocketName") var name: String,
                         @ColumnInfo(name = "description") var description: String,
                         @ColumnInfo(name = "engines_count") var enginesCount: Int)