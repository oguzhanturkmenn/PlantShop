package com.oguzhanturkmen.myplantapp.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.oguzhanturkmen.myplantapp.data.models.Answer
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.data.repo.PlantRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    var plantRepo: PlantRepo,
    var firebaseAuth: FirebaseAuth
) : ViewModel() {
    val list = MutableLiveData<List<Plant>>()
    var answer = MutableLiveData<Answer>()
    val basketPlantList = MutableLiveData<List<Plant>>()


    init {
        getDatas()
    }

    fun getDatas() {
        CoroutineScope(Dispatchers.Main).launch {
            list.value = plantRepo.loadPlants()
        }
    }




/*
    fun addToBasket(plantName:String,plantPrice:String){
        CoroutineScope(Dispatchers.Main).launch {
            plantRepo.addToBasket(plantName,plantPrice)
        }
    }
 */

}