package com.okawa.rockets

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import com.okawa.rockets.di.component.DaggerAppComponent
import com.okawa.rockets.utils.NetworkReceiver
import com.okawa.rockets.utils.manager.ConnectionManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector, HasBroadcastReceiverInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var broadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>

    @Inject
    lateinit var connectionManager: ConnectionManager

    @Inject
    lateinit var networkReceiver: NetworkReceiver

    override fun activityInjector() = activityInjector

    override fun broadcastReceiverInjector() = broadcastReceiverInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initConnectionManager()
    }

    private fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    private  fun initConnectionManager() {
        connectionManager.registerConnectivityMonitor(networkReceiver)
    }

}