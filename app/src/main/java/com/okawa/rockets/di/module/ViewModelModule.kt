package com.okawa.rockets.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okawa.rockets.di.annotation.ViewModelKey
import com.okawa.rockets.di.factory.ViewModelFactory
import com.okawa.rockets.ui.main.details.RocketDetailsViewModel
import com.okawa.rockets.ui.main.list.RocketsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RocketsListViewModel::class)
    abstract fun bindsRocketsListViewModel(rocketsListViewModel: RocketsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RocketDetailsViewModel::class)
    abstract fun bindsRocketDetailsViewModel(rocketDetailsViewModel: RocketDetailsViewModel): ViewModel

}