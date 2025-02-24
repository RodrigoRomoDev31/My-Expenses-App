package com.core.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val EXPENSES_PATTERN = "dd/MM/yyyy HH:mm"

fun getCurrentTimestamp(): Long {
    return System.currentTimeMillis()
}

fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat(EXPENSES_PATTERN, Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}
