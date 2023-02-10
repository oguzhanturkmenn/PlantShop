package com.oguzhanturkmen.myplantapp.ui.profile

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.databinding.FragmentLoginBindingImpl
import com.oguzhanturkmen.myplantapp.databinding.FragmentProfileBinding
import com.oguzhanturkmen.myplantapp.utils.gecisYap

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileFragment = this
        viewModel.getLiveUser()
        observeLiveData()
    }


    fun observeLiveData() {
        viewModel.userLive.observe(viewLifecycleOwner) {
            Log.e("deneme",it.toString())
            it.let {
                binding.user = it!!
            }
        }
    }

    fun logout(){
        viewModel.firebaseAuth.signOut()
        Navigation.gecisYap(requireView(),ProfileFragmentDirections.actionProfileFragmentToLoginFragment())


    }

}