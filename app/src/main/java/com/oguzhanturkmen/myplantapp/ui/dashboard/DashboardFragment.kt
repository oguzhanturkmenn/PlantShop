package com.oguzhanturkmen.myplantapp.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.databinding.FragmentDashboardBinding
import com.oguzhanturkmen.myplantapp.ui.basket.BasketAdapter
import com.oguzhanturkmen.myplantapp.ui.basket.BasketViewModel
import com.oguzhanturkmen.myplantapp.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(){
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var basketViewModel: BasketViewModel
    private lateinit var dashboardAdapter: DashboardAdapter
    lateinit var newProduct: Plant



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        basketViewModel = ViewModelProvider(this)[BasketViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dashboard,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardAdapter = DashboardAdapter(requireContext(),basketViewModel)
        binding.dashboardFragment = this
        observeLiveData()
    }

    private fun observeLiveData() {
        dashboardViewModel.list.observe(viewLifecycleOwner){
            /*
            dashboardAdapter = DashboardAdapter(requireContext(),it,dashboardViewModel)
            binding.dashboardAdapter = dashboardAdapter
            binding.rvDashboard.setHasFixedSize(true)

             */
            binding.dashboardAdapter = dashboardAdapter
            dashboardAdapter.submitList(it)
            dashboardAdapter.notifyDataSetChanged()
        }


    }



    fun letsSeeAllPlantsClicked(){
        Navigation.gecisYap(requireView(),R.id.plantFragment)
    }




}