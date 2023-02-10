package com.oguzhanturkmen.myplantapp.ui.basket

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
class BasketViewModel @Inject constructor(var plantRepo: PlantRepo) : ViewModel() {
    val basketList = MutableLiveData<List<Plant>>()

    init {
        getAllBasket()
    }
    fun getAllBasket() {
        CoroutineScope(Dispatchers.Main).launch {
            basketList.value = plantRepo.getAllBasket()
        }
    }
}