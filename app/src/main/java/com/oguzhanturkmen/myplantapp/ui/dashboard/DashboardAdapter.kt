package com.oguzhanturkmen.myplantapp.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.databinding.PlantRowBinding
import com.oguzhanturkmen.myplantapp.utils.gecisYap
import com.oguzhanturkmen.myplantapp.utils.makeToast

class DashboardAdapter(var mContext: Context, var plantList: List<Plant>, var viewModel :DashboardViewModel)
    : RecyclerView.Adapter<DashboardAdapter.DashboardHolder>(){



    class DashboardHolder(val recyclerRowBinding: PlantRowBinding) : RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val binding: PlantRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.plant_row, parent, false
        )
        return DashboardHolder(binding)
    }
    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        val plant = plantList[position]
        val t = holder.recyclerRowBinding
        t.plants = plant

        t.cardView.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToPlantDetailsFragment(plant)
            Navigation.gecisYap(it,action)
        }

        t.btnAddtobasketPlantrow.setOnClickListener {
            val plantName = plant.PlantName
            val plantPrice = plant.Price
            viewModel.addToBasket(plantName,plantPrice)
            makeToast(mContext,"Added to basket")

        }


    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    /*
    override fun addToBasketClickListener(plant: Plant) {
        android:onClick="@{() -> listener.addToBasketClickListener(Plants)}"

        Toast.makeText(mContext,"asd",Toast.LENGTH_SHORT).show()

        val plantDescription = plant.Description
        val plantName = plant.PlantName
        val plantPrice = plant.Price
        viewModel.addToBasket(plantDescription,plantName,plantPrice)

    }


     */

}