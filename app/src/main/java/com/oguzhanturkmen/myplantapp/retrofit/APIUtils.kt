package com.oguzhanturkmen.mypharmacyonduty.service

import com.oguzhanturkmen.myplantapp.retrofit.PlantAPI
import com.oguzhanturkmen.myplantapp.retrofit.RetrofitBuilder


class APIUtils {
    companion object{
        val BASE_URL = "https://gist.githubusercontent.com/"

        fun plantAPIGet() : PlantAPI {
            return RetrofitBuilder
                .getClient(BASE_URL)
                .create(PlantAPI::class.java)
        }
    }

}