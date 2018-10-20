package com.okawa.rockets.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.okawa.rockets.db.converter.DateTypeConverter
import com.okawa.rockets.db.dao.LaunchDao
import com.okawa.rockets.db.dao.RocketDao
import com.okawa.rockets.db.entity.LaunchEntity
import com.okawa.rockets.db.entity.RocketEntity

@Database(entities = [ LaunchEntity::class, RocketEntity::class ], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class RocketsDatabase: RoomDatabase() {

    abstract fun getLaunchDao() : LaunchDao

    abstract fun getRocketDao() : RocketDao

}