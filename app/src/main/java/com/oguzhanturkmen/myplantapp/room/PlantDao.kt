package com.oguzhanturkmen.myplantapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.oguzhanturkmen.myplantapp.data.models.Answer
import com.oguzhanturkmen.myplantapp.data.models.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM newone_plant")
    suspend fun getAllBasket(): List<Plant>

    @Insert
    suspend fun saveBasket(plant:Plant)

    @Query("SELECT * FROM newone_plant WHERE plant_name = :plantName")
    suspend fun getPlantByName(plantName: String): Plant

    @Update
    suspend fun updatePlant(plant: Plant)

}