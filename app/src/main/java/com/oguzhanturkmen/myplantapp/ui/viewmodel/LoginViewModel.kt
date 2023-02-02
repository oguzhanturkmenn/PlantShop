package com.oguzhanturkmen.myplantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanturkmen.myplantapp.data.models.Answer
import com.oguzhanturkmen.myplantapp.data.repo.PlantRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var plantRepo: PlantRepo
) : ViewModel() {
    var answer = MutableLiveData<Answer>()

    fun login(email: String, password: String){
        answer = plantRepo.login(email,password)
    }
}