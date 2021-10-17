package com.moataz.afternoonhadeeth.data.model

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class HomeResponse(
    val firstImage: List<FirstImage>,
    val live: List<Live>,
    val dailyPost: List<DailyPost>,
    val kanzHasanat: List<KanzHasanat>,
    val tahzeebMuslim: List<TahzeebMuslim>,
    val dailyImage: List<DailyImage>,
    val saheehBukhari: List<SaheehBukhari>,
    val saheehMuslim: List<SaheehMuslim>,
)
@BindingAdapter("imageUrl")
fun loadImage(view: View, imageUrl: String?) {
    val image: ImageView = view as ImageView
    Glide.with(image.context)
        .load(imageUrl)
        .into(image)
}