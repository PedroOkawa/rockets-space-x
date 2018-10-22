package com.okawa.rockets.di.module

import com.okawa.rockets.repository.launch.LaunchRepository
import com.okawa.rockets.repository.launch.LaunchRepositoryImpl
import com.okawa.rockets.repository.rocket.RocketRepository
import com.okawa.rockets.repository.rocket.RocketRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindLaunchRepository(launchRepositoryImpl: LaunchRepositoryImpl) : LaunchRepository

    @Binds
    abstract fun bindRocketRepository(rocketRepositoryImpl: RocketRepositoryImpl) : RocketRepository

}