package com.core.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val EXPENSES_PATTERN = "dd/MM/yyyy HH:mm"
private const val MONTH_PATTERN = "MMMM yyyy"
private const val TIME_PATTERN = "hh:mm a"
private const val DAY_FORMAT = "EEEE, dd MMMM yyyy, hh:mm a"

fun getCurrentTimestamp(): Long {
    return System.currentTimeMillis()
}

fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat(EXPENSES_PATTERN, Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}

fun formatTimestampTime(timestamp: Long): String {
    val dateFormat = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}

fun getExpenseMonth(timestamp: Long = System.currentTimeMillis()): String {
    val dateFormat = SimpleDateFormat(MONTH_PATTERN, Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}

fun getExpenseDate(timestamp: Long = System.currentTimeMillis()): String {
    val dateFormat = SimpleDateFormat(DAY_FORMAT, Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}