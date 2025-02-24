package com.core.ui.utils

enum class ExpensesTypes(val type: String) {
    Entertainment("Entertainment"),
    Food("Food"),
    StreamingServices("Streaming Services"),
    Stationary("Stationary"),
    HomeLoan("Home Loan"),
    PersonalLoan("Personal Loan");

    companion object {
        private val map = entries.associateBy(ExpensesTypes::type)

        fun withType(type: String) = map[type] ?: ""
    }
}