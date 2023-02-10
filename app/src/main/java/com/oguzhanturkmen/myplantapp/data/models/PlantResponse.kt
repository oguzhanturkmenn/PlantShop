package com.oguzhanturkmen.myplantapp.data.models

import com.oguzhanturkmen.myplantapp.data.models.Plant

data class PlantResponse(
    val `data`: List<Plant>,
    var success: Int?,
    val message: String,
    val status: String
)