package com.oguzhanturkmen.myplantapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oguzhanturkmen.myplantapp.data.models.Answer
import com.oguzhanturkmen.myplantapp.data.models.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM new_plant")
    suspend fun getAllBasket(): List<Plant>

    @Insert
    suspend fun saveBasket(plant:Plant)


}