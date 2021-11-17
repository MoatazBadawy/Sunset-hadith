package com.moataz.afternoonhadeeth.utils.helper

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.moataz.afternoonhadeeth.R

@BindingAdapter("imageUrl")
fun loadImage(view: View, imageUrl: String?) {
    val image: ImageView = view as ImageView
    Glide.with(image.context)
        .load(imageUrl)
        .into(image)
}

@BindingAdapter("imageUrlWithPlaceHolder")
fun loadImageWithPlaceHolder(view: View, imageUrl: String?) {
    val image: ImageView = view as ImageView
    Glide.with(image.context)
        .load(imageUrl)
        .placeholder(R.drawable.folder_loading_image)
        .into(image)
}