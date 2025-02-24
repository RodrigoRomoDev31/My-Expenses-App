package com.romvaz.room

import android.content.Context
import androidx.room.Room
import com.romvaz.room.databases.expenses.ExpensesDao
import com.romvaz.room.databases.expenses.ExpensesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideExpensesDatabase(@ApplicationContext context: Context): ExpensesDatabase {
        return Room.databaseBuilder(
            context,
            ExpensesDatabase::class.java,
            "expenses_database"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideExpensesDao(database: ExpensesDatabase): ExpensesDao {
        return database.dao
    }
}
