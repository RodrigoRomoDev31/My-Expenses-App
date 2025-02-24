package com.romvaz.room.databases.test

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.domain.model.room.ExpensesRoomModel

@Database(
    entities = [ExpensesRoomModel::class],
    version = 1,
    exportSchema = true
)

abstract class ExpensesDatabase : RoomDatabase() {
    abstract val dao: ExpensesDao
}
