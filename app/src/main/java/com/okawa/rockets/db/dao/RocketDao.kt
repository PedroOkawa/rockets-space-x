package com.okawa.rockets.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.okawa.rockets.db.entity.RocketEntity

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rockets: List<RocketEntity>)

    @Query("SELECT * FROM rocket")
    fun loadRockets() : DataSource.Factory<Int, RocketEntity>

    @Query("SELECT * FROM rocket WHERE active = 1")
    fun loadActiveRockets() : DataSource.Factory<Int, RocketEntity>

}