package com.oguzhanturkmen.myplantapp.data.repo

import com.oguzhanturkmen.myplantapp.data.datasource.PlantDataSource
import com.oguzhanturkmen.myplantapp.data.models.Plant

class PlantRepo(var dataSource: PlantDataSource) {

    suspend fun loadPlants(): List<Plant> = dataSource.loadPlants()

    suspend fun addToBasket(plantName:String,plantPrice:String) = dataSource.addToBasket(plantName,plantPrice)

    suspend fun getAllBasket(): List<Plant> = dataSource.getAllBasket()

    fun login(email: String, password: String) = dataSource.login(email, password)

    fun register(userEmail: String, userPassword: String) =
        dataSource.register(userEmail, userPassword)

    fun getLiveUser() = dataSource.getLiveUser()

    fun updateImage(imageUrl: String) = dataSource.updateImage(imageUrl)



}