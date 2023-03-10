package com.oguzhanturkmen.myplantapp.ui.basket

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.databinding.FragmentBasketBinding
import com.oguzhanturkmen.myplantapp.ui.dashboard.DashboardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() , BasketAdapterListener {
    private lateinit var binding: FragmentBasketBinding
    private var data:Plant? = null
    private lateinit var basketAdapter: BasketAdapter
    private lateinit var viewModel: BasketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BasketViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basket,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basketAdapter = BasketAdapter(this@BasketFragment)
        viewModel.getAllBasket()
        observeLiveData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveData() {

        viewModel.basketList.observe(viewLifecycleOwner){
            binding.basketAdapter = basketAdapter
            basketAdapter.submitList(it)
            basketAdapter.notifyDataSetChanged()
        }

    }

    override fun onDeleteClicked(plant: Plant) {
        viewModel.deletePlant(plant)
    }
}