package com.okawa.rockets.di.module

import com.okawa.rockets.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ FragmentBuilderModule::class ])
    abstract fun contributesMainActivity(): MainActivity

}