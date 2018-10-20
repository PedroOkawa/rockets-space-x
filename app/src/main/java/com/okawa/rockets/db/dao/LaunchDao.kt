package com.okawa.rockets.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.okawa.rockets.db.entity.LaunchEntity

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rockets: List<LaunchEntity>)

}