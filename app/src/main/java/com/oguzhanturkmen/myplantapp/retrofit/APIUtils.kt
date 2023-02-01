package com.oguzhanturkmen.mypharmacyonduty.service

import com.oguzhanturkmen.myplantapp.retrofit.PlantAPI
import com.oguzhanturkmen.myplantapp.retrofit.RetrofitBuilder


class APIUtils {
    companion object{

        fun plantAPIGet() : PlantAPI {
            return RetrofitBuilder.getClient("https://gist.githubusercontent.com/")
                .create(PlantAPI::class.java)
        }
    }

}