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

    @Query("SELECT * FROM room_plant")
    suspend fun getAllBasket(): List<Plant>

    @Insert
    suspend fun saveBasket(plant:Plant)

    @Query("SELECT * FROM room_plant WHERE plant_name = :plantName")
    suspend fun getPlantByName(plantName: String): Plant

    @Query("SELECT * FROM room_plant WHERE plant_name LIKE :query ORDER BY plant_id DESC")
    fun searchPlant(query: String?): List<Plant>

    @Query("UPDATE room_plant SET plant_count=:count WHERE plant_name = :name")
    fun updatePlant(count: Int, name: String)

    @Query("DELETE FROM room_plant WHERE plant_name = :name")
    suspend fun deletePlant(name: String)

}