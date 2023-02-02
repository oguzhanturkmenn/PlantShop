package com.oguzhanturkmen.myplantapp.data.repo

import com.oguzhanturkmen.myplantapp.data.datasource.PlantDataSource

class PlantRepo(var dataSource: PlantDataSource) {
    fun login(email: String, password: String) = dataSource.login(email, password)

    fun register(userEmail: String, userPassword: String) =
        dataSource.register(userEmail, userPassword)

    fun getLiveUser() = dataSource.getLiveUser()
}