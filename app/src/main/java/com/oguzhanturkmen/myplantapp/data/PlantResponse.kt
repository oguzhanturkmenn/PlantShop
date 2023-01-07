package com.oguzhanturkmen.myplantapp.data

data class PlantResponse(
    val `data`: List<Plant>,
    val message: String,
    val status: String
)