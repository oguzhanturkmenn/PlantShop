package com.oguzhanturkmen.myplantapp.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.databinding.FragmentSignUpBinding
import com.oguzhanturkmen.myplantapp.utils.gecisYap
import com.oguzhanturkmen.myplantapp.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpFragment = this
    }

    private fun observeLiveData() {
        viewModel.answer.observe(viewLifecycleOwner) { answer ->
            answer.success?.let {
                if (it == 1) {
                    Navigation.gecisYap(requireView(),R.id.dashboardFragment)
                    makeToast(requireContext(), "Kayıt oluşturulmuştur")
                } else {
                    makeToast(requireContext(), answer.message.toString())
                }
            }
        }
    }

    fun signUpClicked(email: String?, password: String?,passwordAgain:String?){
        if (email != "" && password != "" && passwordAgain != "") {
            email?.let { nEmail ->
                password?.let { nPassword ->
                    if (password == passwordAgain) {
                        viewModel.register(nEmail, nPassword)
                        observeLiveData()
                    } else makeToast(requireContext(),"parola yanlış")
                }
            }
        } else makeToast(requireContext(), "boş olmamalı")
    }

}