package com.moataz.afternoonhadeeth.data.model.home

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