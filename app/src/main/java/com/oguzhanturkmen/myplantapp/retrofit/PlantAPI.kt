package com.oguzhanturkmen.myplantapp.retrofit


import com.oguzhanturkmen.myplantapp.data.models.PlantResponse
import retrofit2.Call
import retrofit2.http.GET


interface PlantAPI {

    @GET("oguzhanturkmenn/bd8ee4476e50f4b7cc1ebffceb82cf6b/raw/ec9744817364e3f8ef4f4bc429399a387a31cf8e/plants.json")
    suspend fun getPlant(): PlantResponse
}