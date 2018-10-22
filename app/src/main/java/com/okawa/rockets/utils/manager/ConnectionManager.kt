package com.okawa.rockets.utils.manager

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import com.okawa.rockets.utils.NetworkReceiver
import com.okawa.rockets.utils.bus.ConnectionBus
import javax.inject.Inject

class ConnectionManager @Inject constructor(
    private val application: Application,
    private val connectionBus: ConnectionBus
) {

    private val isConnected: Boolean
        get() {
            val connectivityManager =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = connectivityManager.activeNetworkInfo

            return activeNetwork != null
        }

    fun postConnectionEvent() {
        connectionBus.postValue(isConnected)
    }

    @SuppressLint("NewApi")
    fun registerConnectivityMonitor(networkReceiver: NetworkReceiver) {
        val filter = IntentFilter()

        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction(ACTION_CONNECTIVITY_CHANGE)

        application.registerReceiver(networkReceiver, filter)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return
        }

        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    getConnectivityIntent(false)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    getConnectivityIntent(true)
                }
            })
    }

    private fun getConnectivityIntent(noConnection: Boolean): Intent {
        val intent = Intent()

        intent.action = ACTION_CONNECTIVITY_CHANGE
        intent.putExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, noConnection)

        return intent
    }

    companion object {

        private val ACTION_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
    }

}