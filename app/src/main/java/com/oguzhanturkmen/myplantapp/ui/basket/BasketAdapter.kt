package com.oguzhanturkmen.myplantapp.ui.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.databinding.BasketRowBinding
import com.oguzhanturkmen.myplantapp.databinding.PlantRowBinding

class BasketAdapter(var basketPlantList: List<Plant>) : RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    class BasketHolder(val recyclerRowBinding: BasketRowBinding) : RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<BasketRowBinding>(
            inflater, R.layout.basket_row, parent, false
        )
        return BasketHolder(binding)
    }



    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val plant = basketPlantList[position]
        val t = holder.recyclerRowBinding
        t.plant = plant

    }

    override fun getItemCount(): Int {
        return basketPlantList.size
    }


}
