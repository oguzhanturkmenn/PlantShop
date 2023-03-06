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
    val basketPlantList = MutableLiveData<List<Plant>>()


    init {
        getAllBasket()
    }
    fun getAllBasket() {
        CoroutineScope(Dispatchers.Main).launch {
            basketList.value = plantRepo.getAllBasket()
        }
    }

    fun addToBasket(plant:Plant, plantName:String, plantPrice:String, plantCount:Int){
        CoroutineScope(Dispatchers.Main).launch {
            if (basketPlantList.value != null){
                val arrayList = ArrayList(basketPlantList.value!!)
                //contains: eğer bu listenin içine eklenmeye çalışan ürün halihazırda varsa demek.
                if (arrayList.contains(plant)){
                    //indexOfFirst: istediğim elemanın hangi indexte olduğunu söylüyor.
                    //indexOfFirst'e tanımlanan şey mesela telefon bu dizinin içinde var
                    //ve 2. indexin içinde var diye bana bunu veriyor.
                    val indexOfFirst = arrayList.indexOfFirst { it == plant }
                    val relatedProduct = arrayList.get(indexOfFirst)
                    relatedProduct.plantCount += 1
                    basketPlantList.value = arrayList

                }else{
                    plant.plantCount += 1
                    arrayList.add(plant)
                    basketPlantList.value = arrayList
                }
            }else{
                val arrayList = arrayListOf(plant)
                plant.plantCount += 1
                basketPlantList.value = arrayList
            }

            basketPlantList.value.let {
                // refreshTotalValue(it!!)
            }
            plantRepo.addToBasket(plantName,plantPrice,plantCount)
        }

    }


/*
    private fun refreshTotalValue(listOfProduct:List<Product>){
        var total = 0
        //total değeri almak için önce ürün listesini alırız ve bu listeyi tek tek işlicez.
        //forEach tek tek bana ürünleri verecek.
        listOfProduct.forEach { product ->
            val price = product.price.toIntOrNull()
            price.let {
                //productan kaç tane var onu alırız
                val count = product.count
                //mesela 3 telefon var bunların getirdiği toplam ciroyu hesaplama algoritması
                val revenue = count * it!!
                //ciroyu totale eşitleriz
                total += revenue
                //total toplam değerimizi verir ve totalbasket mutablelive'e eşitleriz
            }
        }
        totalBasket.value = total
    }

 */



}