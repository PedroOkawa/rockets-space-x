package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject

class RocketsListViewModel @Inject constructor(): ViewModel() {

    fun test() {
        Log.i("TEST", "TEST")
    }

}