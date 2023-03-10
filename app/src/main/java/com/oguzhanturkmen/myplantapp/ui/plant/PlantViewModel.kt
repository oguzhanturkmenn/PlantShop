package com.oguzhanturkmen.myplantapp.ui.plant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.data.repo.PlantRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(var plantRepo: PlantRepo) : ViewModel() {
    val list = MutableLiveData<List<Plant>>()

    init {
        getPlantFragmentDatas()
    }

    fun getPlantFragmentDatas() {
        CoroutineScope(Dispatchers.Main).launch {
            list.value = plantRepo.loadPlants()
        }
    }

    fun searchPlant(word:String){
        CoroutineScope(Dispatchers.Main).launch {
            list.value = plantRepo.searchPlant(word)
        }
    }
}