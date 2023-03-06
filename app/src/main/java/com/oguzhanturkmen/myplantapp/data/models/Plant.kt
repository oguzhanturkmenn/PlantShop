package com.oguzhanturkmen.myplantapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "newone_plant")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id") @NotNull var plant_id: Int,
    @ColumnInfo(name = "plant_description") @NotNull var Description: String,
    @ColumnInfo(name = "plant_image") @NotNull var Image: String,
    @ColumnInfo(name = "plant_price") @NotNull var Price: String,
    @ColumnInfo(name = "plant_name") @NotNull var PlantName: String,
    @ColumnInfo(name = "plant_count") @NotNull var plantCount: Int = 0,
    @ColumnInfo(name = "plant_revenue") @NotNull var plantRevenue: Int = 0
    ) :Serializable{

}