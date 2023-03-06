package com.oguzhanturkmen.myplantapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhanturkmen.myplantapp.data.models.Plant

@Database(entities = [Plant::class], version = 1)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun getPlantRoomDao(): PlantDao
}