package com.core.domain.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpensesRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val expenseType: String,
    val expense: String,
    val date: String
)
