package com.okawa.rockets.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.okawa.rockets.utils.manager.ConnectionManager
import javax.inject.Inject

class NetworkReceiver @Inject constructor(private val connectionManager: ConnectionManager): BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        connectionManager.postConnectionEvent()
    }
}