package com.oguzhanturkmen.myplantapp.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.databinding.PlantRowBinding
import com.oguzhanturkmen.myplantapp.ui.basket.BasketAdapter
import com.oguzhanturkmen.myplantapp.ui.basket.BasketViewModel
import com.oguzhanturkmen.myplantapp.utils.gecisYap
import com.oguzhanturkmen.myplantapp.utils.makeToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardAdapter(
    var mContext: Context,
    var viewModel: BasketViewModel
) : ListAdapter<Plant, DashboardAdapter.DashboardHolder>(DashboardAdapter.DashboardPlantDiffCallback()) {


    class DashboardHolder(val recyclerRowBinding: PlantRowBinding) :
        RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val binding: PlantRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.plant_row, parent, false
        )
        return DashboardHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        val plant = currentList[position]
        val t = holder.recyclerRowBinding
        t.plants = plant

        t.cardView.setOnClickListener {
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToPlantDetailsFragment(plant)
            Navigation.gecisYap(it, action)
        }

        t.btnAddtobasketPlantrow.setOnClickListener {
            viewModel.addToBasket(plant)
            makeToast(mContext, "Added to basket")
        }
    }

    class DashboardPlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.plant_id == newItem.plant_id
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.PlantName == newItem.PlantName && oldItem.Price == newItem.Price
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


}