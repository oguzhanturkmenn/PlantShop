package com.oguzhanturkmen.myplantapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "room_plant")
data class Plant(
    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id")
    var plant_id: Int,
    @ColumnInfo(name = "plant_description")
    var Description: String?,
    @ColumnInfo(name = "plant_image")
    var Image: String?,
    @ColumnInfo(name = "plant_price")
    var Price: String?,
    @ColumnInfo(name = "plant_name")
    var PlantName: String?,
    @ColumnInfo(name = "plant_count")
    var plantCount: Int?,
    @ColumnInfo(name = "plant_email")
    var plantEmail: String?
) :Serializable