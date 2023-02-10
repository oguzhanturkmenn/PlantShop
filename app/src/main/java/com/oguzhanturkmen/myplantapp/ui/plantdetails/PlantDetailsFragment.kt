package com.oguzhanturkmen.myplantapp.ui.plantdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.databinding.FragmentPlantDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPlantDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_plant_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgument()
    }

    fun getArgument() {
        arguments?.let {
            val gelenData = PlantDetailsFragmentArgs.fromBundle(it).plantDetailData
            binding.plants = gelenData
        }
    }


}