package com.okawa.rockets.utils.extensions

import android.support.annotation.StringRes
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

object TextViewExtensionsValues {

    const val DATE_FORMAT_PATTERN = "dd/MM/yyyy"
    const val BOOLEAN_TRUE_VALUE = "YES"
    const val BOOLEAN_FALSE_VALUE = "NO"
    const val BOOLEAN_NULL_VALUE = "N/A"

}

fun TextView.setTextDate(date: Date?) {
    val dateFormat = SimpleDateFormat(TextViewExtensionsValues.DATE_FORMAT_PATTERN, Locale.getDefault())
    text = dateFormat.format(date)
}

fun TextView.setTextYear(date: Date?) {
    val dateFormat = SimpleDateFormat(TextViewExtensionsValues.DATE_FORMAT_PATTERN, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = date
    val year = calendar.get(Calendar.YEAR)
    text = year.toString()
}

fun TextView.setTextBoolean(@StringRes textId: Int, value: Boolean?) {
    val valueText = when(value) {
        true -> TextViewExtensionsValues.BOOLEAN_TRUE_VALUE
        false -> TextViewExtensionsValues.BOOLEAN_FALSE_VALUE
        null -> TextViewExtensionsValues.BOOLEAN_NULL_VALUE
    }
    text = context.getString(textId, valueText)
}