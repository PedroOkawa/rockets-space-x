package com.okawa.rockets.utils

import android.app.Application
import android.support.annotation.StringRes
import android.widget.Toast
import javax.inject.Inject

class ToastManager @Inject constructor(private val application: Application) {

    fun showToast(message: String?) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes messageId: Int) {
        Toast.makeText(application, messageId, Toast.LENGTH_SHORT).show()
    }

}