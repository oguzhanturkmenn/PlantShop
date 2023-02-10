package com.oguzhanturkmen.myplantapp.ui.plant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.databinding.PlantRowBinding
import com.oguzhanturkmen.myplantapp.ui.dashboard.DashboardAdapter
import com.oguzhanturkmen.myplantapp.ui.dashboard.DashboardFragmentDirections
import com.oguzhanturkmen.myplantapp.utils.gecisYap

class PlantAdapter(var plantList: List<Plant>) : RecyclerView.Adapter<PlantAdapter.PlantHolder>() {

    class PlantHolder(val recyclerRowBinding: PlantRowBinding) : RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PlantRowBinding>(
            inflater, R.layout.plant_row, parent, false
        )
        return PlantHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        val plant = plantList[position]
        val t = holder.recyclerRowBinding
        t.plants = plant

        t.cardView.setOnClickListener {
            val action = PlantFragmentDirections.actionPlantFragmentToPlantDetailsFragment(plant)
            Navigation.gecisYap(it,action)
        }
    }

    override fun getItemCount(): Int {
        return plantList.size
    }


}

