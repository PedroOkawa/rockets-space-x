package com.okawa.rockets.di.module

import com.okawa.rockets.repository.RocketRepository
import com.okawa.rockets.repository.RocketRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRocketRepository(rocketRepositoryImpl: RocketRepositoryImpl) : RocketRepository

}