package com.oguzhanturkmen.mypharmacyonduty.service


class APIUtils {
    companion object{

        fun plantAPIGet() : PlantAPI{
            return RetrofitBuilder.getClient("https://gist.githubusercontent.com/")
                .create(PlantAPI::class.java)
        }
    }

}