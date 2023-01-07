package com.oguzhanturkmen.myplantapp.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.adapter.DashboardAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var layoutManager :LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        dashboardViewModel.getDatas()

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        observeLiveData()
    }

    private fun observeLiveData() {
        dashboardViewModel.list.observe(viewLifecycleOwner){
            dashboardAdapter = DashboardAdapter(it)
            rv_dashboard.adapter = dashboardAdapter
            rv_dashboard.layoutManager = layoutManager
            rv_dashboard.setHasFixedSize(true)
        }
    }
}