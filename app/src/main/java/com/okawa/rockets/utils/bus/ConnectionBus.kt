package com.okawa.rockets.utils.bus

import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionBus @Inject constructor() : MutableLiveData<Boolean>()