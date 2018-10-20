package com.okawa.rockets.di.module

import com.okawa.rockets.ui.main.RocketDetailsFragment
import com.okawa.rockets.ui.main.rocketslist.RocketsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesRocketsListFragment(): RocketsListFragment

    @ContributesAndroidInjector
    abstract fun contributesRocketDetailsFragment(): RocketDetailsFragment

}