package com.okawa.rockets.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.okawa.rockets.db.RocketsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        const val DATABASE_NAME = "rockets.db"
    }

    @Singleton
    @Provides
    fun provideDatabase(app: Application) : RocketsDatabase {
        return Room
            .databaseBuilder(app, RocketsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideLaunchDao(database: RocketsDatabase) = database.getLaunchDao()

    @Singleton
    @Provides
    fun provideRocketDao(database: RocketsDatabase) = database.getRocketDao()

}