package com.oguzhanturkmen.myplantapp.ui.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.databinding.BasketRowBinding
import com.oguzhanturkmen.myplantapp.databinding.PlantRowBinding

class BasketAdapter(val listener: BasketAdapterListener) :
    ListAdapter<Plant, BasketAdapter.BasketHolder>(BasketPlantDiffCallback()) {

    class BasketHolder(val recyclerRowBinding: BasketRowBinding) :
        RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<BasketRowBinding>(
            inflater, R.layout.basket_row, parent, false
        )
        return BasketHolder(binding)
    }


    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val plant = currentList[position]
        val t = holder.recyclerRowBinding
        t.plant = plant
        t.tvPlantPieceBasketrow.text = plant.plantCount.toString()
        t.productAddToFavPredictProduct.setOnClickListener {
            listener.onDeleteClicked(plant)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class BasketPlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.plant_id == newItem.plant_id
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.PlantName == newItem.PlantName && oldItem.Price == newItem.Price
        }
    }

}

interface BasketAdapterListener {
    fun onDeleteClicked(plant: Plant)
}
