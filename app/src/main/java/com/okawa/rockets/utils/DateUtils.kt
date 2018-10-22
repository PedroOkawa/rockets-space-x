package com.okawa.rockets.utils

import java.util.*
import javax.inject.Inject

class DateUtils @Inject constructor() {

    fun checkSameYear(dateA: Date?, dateB: Date?): Boolean {
        if(dateA == null || dateB == null) {
            return false
        }
        val calendar = Calendar.getInstance()
        calendar.time = dateA
        val yearA = calendar.get(Calendar.YEAR)
        calendar.time = dateB
        val yearB = calendar.get(Calendar.YEAR)
        return yearA == yearB
    }

}