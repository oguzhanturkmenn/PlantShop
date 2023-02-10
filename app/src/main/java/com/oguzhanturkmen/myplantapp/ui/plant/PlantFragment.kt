package com.oguzhanturkmen.myplantapp.ui.plant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.databinding.FragmentPlantBinding
import com.oguzhanturkmen.myplantapp.ui.dashboard.DashboardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantFragment : Fragment() {
    private lateinit var binding: FragmentPlantBinding
    private lateinit var viewModel: PlantViewModel
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PlantViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_plant,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.list.observe(viewLifecycleOwner){
            plantAdapter = PlantAdapter(it)
            binding.plantAdapter = plantAdapter
            binding.rvPlant.setHasFixedSize(true)
    }
}


}