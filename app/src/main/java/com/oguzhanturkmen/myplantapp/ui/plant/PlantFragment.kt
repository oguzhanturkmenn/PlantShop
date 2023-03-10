package com.oguzhanturkmen.myplantapp.ui.plant

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.databinding.FragmentPlantBinding
import com.oguzhanturkmen.myplantapp.ui.dashboard.DashboardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentPlantBinding
    private lateinit var viewModel: PlantViewModel
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PlantViewModel::class.java]
        setHasOptionsMenu(true)


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

        setUpMenu()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.list.observe(viewLifecycleOwner){
            plantAdapter = PlantAdapter(it)
            binding.plantAdapter = plantAdapter
            binding.rvPlant.setHasFixedSize(true)
        }
    }

    private fun setUpMenu() {

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)

                val item = menu.findItem(R.id.search_menu_id)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@PlantFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.search_menu_id -> {
                        return true
                    }
                    else -> {
                        return false
                    }
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchPlant(query)
            return true
        }else {
            return false
        }

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchPlant(newText)
            return true
        }else {
            return false
        }
    }

    private fun searchPlant(query: String?) {
        val searchQuery = "%$query%"
        viewModel.searchPlant(searchQuery)
    }


}