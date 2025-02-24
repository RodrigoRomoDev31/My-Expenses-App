package com.romvaz.myexpensesapp.di

import android.content.Context
import com.core.data.DataModule
import com.core.ui.navigation.ComposeNavigator
import com.core.ui.navigation.Navigator
import com.romvaz.myexpensesapp.MyExpensesAppApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DataModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context): MyExpensesAppApplication {
        return app as MyExpensesAppApplication
    }

    @Provides
    @Singleton
    fun providesNavigator(): Navigator =
        ComposeNavigator()
}
