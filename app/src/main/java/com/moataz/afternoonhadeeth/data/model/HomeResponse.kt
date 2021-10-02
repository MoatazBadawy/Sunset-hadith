package com.moataz.afternoonhadeeth.data.model

data class HomeResponse(
    val dailyImage: List<DailyImage>?,
    val firstImage: List<FirstImage>?,
    val kanzHasanat: List<KanzHasanat>?,
    val tahzeebMuslim: List<TahzeebMuslim>?,
    val live: List<Live>?,
    val saheehBukhari: List<SaheehBukhari>?,
    val saheehMuslim: List<SaheehMuslim>?
)
