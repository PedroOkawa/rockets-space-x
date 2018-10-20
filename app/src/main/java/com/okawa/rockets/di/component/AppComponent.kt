package com.okawa.rockets.di.component

import android.app.Application
import com.okawa.rockets.App
import com.okawa.rockets.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules =
[
    ActivityBuilderModule::class,
    AndroidSupportInjectionModule::class,
    ApiModule::class,
    DatabaseModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)

}