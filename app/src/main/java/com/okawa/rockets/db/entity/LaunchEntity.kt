package com.okawa.rockets.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "launch")
data class LaunchEntity(@PrimaryKey @ColumnInfo(name = "flight_number") var flightNumber: Long = 0,
                        @ColumnInfo(name = "rocket_id") var rocketId: String,
                        @ColumnInfo(name = "launch_date") var launchDate: Date)