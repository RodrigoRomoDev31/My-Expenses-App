package com.core.ui.utils

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val MONTH_PATTERN = "MMMM yyyy"
private const val DAY_FORMAT = "EEEE, dd MMMM yyyy, hh:mm a"

fun getCurrentTimestamp(): Long {
    return System.currentTimeMillis()
}

fun getCurrentTimestampWithAddedMonth(): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MONTH, 1)
    return calendar.timeInMillis
}

fun getExpenseMonth(timestamp: Long = System.currentTimeMillis()): String {
    val dateFormat = SimpleDateFormat(MONTH_PATTERN, Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}

fun getExpenseDate(timestamp: Long = System.currentTimeMillis()): String {
    val dateFormat = SimpleDateFormat(DAY_FORMAT, Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}

fun getColorForExpenseType(expenseType: String): Color {
    return when (expenseType) {
        ExpensesTypes.Entertainment.type -> Color(0xFF6200EE) // Purple
        ExpensesTypes.Food.type -> Color(0xFFFF5722) // Orange
        ExpensesTypes.StreamingServices.type -> Color(0xFF03DAC5) // Teal
        ExpensesTypes.Stationary.type -> Color(0xFF9E9E9E) // Gray
        ExpensesTypes.HomeLoan.type -> Color(0xFF3F51B5) // Blue
        ExpensesTypes.PersonalLoan.type -> Color(0xFFFF9800) // Amber
        else -> Color(0xFFFF9800)
    }
}