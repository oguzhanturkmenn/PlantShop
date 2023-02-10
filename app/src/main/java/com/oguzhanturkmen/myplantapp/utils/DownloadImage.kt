package com.oguzhanturkmen.myplantapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.oguzhanturkmen.myplantapp.R


@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}