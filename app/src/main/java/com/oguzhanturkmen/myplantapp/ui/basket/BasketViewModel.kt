package com.oguzhanturkmen.myplantapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun addToBasket(plant: Plant) {
        viewModelScope.launch {
            var isBasket = false
            var basketCount = 0
            basketList.value?.let {
                for (plantDatabase in it) {
                    if (plantDatabase.PlantName == plant.PlantName) {
                        isBasket = true
                        plantDatabase.plantCount?.let {
                            basketCount = it
                        }
                        break
                    }
                }
                if (isBasket) {
                    plant.plantCount?.let {
                        plant.PlantName?.let { it1 -> plantRepo.deletePlant(it1) }
                        val newPlant = Plant(
                            plant.plant_id,
                            plant.Description,
                            plant.Image,
                            plant.Price,
                            plant.PlantName,
                            basketCount+1,
                            plant.plantEmail
                        )
                        plantRepo.addToBasket(newPlant)
                    }

                } else {
                    val newPlant = Plant(
                        plant.plant_id,
                        plant.Description,
                        plant.Image,
                        plant.Price,
                        plant.PlantName,
                        1,
                        plant.plantEmail
                    )
                    plantRepo.addToBasket(newPlant)
                }
            }
        }
    }

    fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            plant.PlantName?.let { plantRepo.deletePlant(it) }
            getAllBasket()
        }
    }


/*
    private fun refreshTotalValue(listOfProduct:List<Product>){
        var total = 0
        //total de??eri almak i??in ??nce ??r??n listesini al??r??z ve bu listeyi tek tek i??licez.
        //forEach tek tek bana ??r??nleri verecek.
        listOfProduct.forEach { product ->
            val price = product.price.toIntOrNull()
            price.let {
                //productan ka?? tane var onu al??r??z
                val count = product.count
                //mesela 3 telefon var bunlar??n getirdi??i toplam ciroyu hesaplama algoritmas??
                val revenue = count * it!!
                //ciroyu totale e??itleriz
                total += revenue
                //total toplam de??erimizi verir ve totalbasket mutablelive'e e??itleriz
            }
        }
        totalBasket.value = total
    }

 */


}