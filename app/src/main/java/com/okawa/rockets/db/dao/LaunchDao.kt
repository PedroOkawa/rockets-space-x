package com.okawa.rockets.db.dao

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.okawa.rockets.db.entity.LaunchEntity

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rockets: List<LaunchEntity>)

    @Query("SELECT * FROM launch WHERE rocket_id = :rocketId")
    fun loadLaunchesByRocketId(rocketId: String?) : LiveData<List<LaunchEntity>>

}