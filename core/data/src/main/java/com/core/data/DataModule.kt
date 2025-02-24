package com.core.data

import com.romvaz.room.RoomModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        RoomModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DataModule
