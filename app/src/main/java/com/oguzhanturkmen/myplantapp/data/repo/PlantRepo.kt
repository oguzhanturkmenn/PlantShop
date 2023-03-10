package com.oguzhanturkmen.myplantapp.data.repo

import com.oguzhanturkmen.myplantapp.data.datasource.PlantDataSource
import com.oguzhanturkmen.myplantapp.data.models.Plant

class PlantRepo(var dataSource: PlantDataSource) {

    suspend fun loadPlants(): List<Plant> = dataSource.loadPlants()

    suspend fun addToBasket(plant: Plant) = dataSource.addToBasket(plant)

    suspend fun deletePlant(name: String) = dataSource.deletePlant(name)

    suspend fun searchPlant(word: String): List<Plant> = dataSource.searchPlant(word)

    suspend fun updatePlant(count: Int, name: String) = dataSource.updatePlant(count, name)

    suspend fun getAllBasket(): List<Plant> = dataSource.getAllBasket()

    fun login(email: String, password: String) = dataSource.login(email, password)

    fun register(userEmail: String, userPassword: String) =
        dataSource.register(userEmail, userPassword)

    fun getLiveUser() = dataSource.getLiveUser()

    fun updateImage(imageUrl: String) = dataSource.updateImage(imageUrl)



}