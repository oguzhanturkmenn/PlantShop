package com.oguzhanturkmen.myplantapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.gecisYap(view: View, id:Int){
    findNavController(view).navigate(id)

}

fun Navigation.gecisYap(view: View, id: NavDirections){
    findNavController(view).navigate(id)

}

fun makeToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}