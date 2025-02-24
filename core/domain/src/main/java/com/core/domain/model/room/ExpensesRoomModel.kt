package com.core.domain.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpensesRoomModel(
    @PrimaryKey
    val id: String,
    val expenseType: String,
    val expense: Double,
    val date: String
)
