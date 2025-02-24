package com.romvaz.room.databases.test

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.core.domain.model.room.ExpensesRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {

    @Upsert
    suspend fun upsertTest(games: ExpensesRoomModel)

    @Delete
    suspend fun deleteTest(games: ExpensesRoomModel)

    @Query("SELECT * FROM expensesroommodel ORDER BY title ASC")
    fun getTestSortedByTitle(): Flow<List<ExpensesRoomModel>>
}
