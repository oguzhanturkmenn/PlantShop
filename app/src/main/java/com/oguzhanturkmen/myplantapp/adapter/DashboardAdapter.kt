package com.oguzhanturkmen.myplantapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.myplantapp.data.Plant
import com.oguzhanturkmen.myplantapp.databinding.PlantRowBinding

class DashboardAdapter(var plantList: List<Plant>) : RecyclerView.Adapter<DashboardAdapter.DashboardHolder>() {

    class DashboardHolder(val recyclerRowBinding: PlantRowBinding) : RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val recyclerRowBinding: PlantRowBinding = PlantRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        holder.recyclerRowBinding.tvPlantNamePlantrow.text = plantList[position].PlantName
        holder.recyclerRowBinding.tvPlantPricePlantrow.text = plantList[position].Price

    }

    override fun getItemCount(): Int {
        return plantList.size
    }


}