package com.oguzhanturkmen.myplantapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanturkmen.mypharmacyonduty.service.APIUtils
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.data.models.PlantResponse
import retrofit2.Call
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    val list = MutableLiveData<List<Plant>>()

    fun getDatas() {
        APIUtils.plantAPIGet().getPlant().enqueue(
            object : retrofit2.Callback<PlantResponse> {
                override fun onResponse(
                    call: Call<PlantResponse>,
                    response: Response<PlantResponse>
                ) {
                    Log.e("onResponse", response.body().toString())
                    val tempList = response.body()?.data
                    tempList?.let {
                        list.value = it
                    }
                }

                override fun onFailure(call: Call<PlantResponse>, t: Throwable) {
                    t.printStackTrace()
                }


            })
    }
}