package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.APIService
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.data.request.RetroInstant
import io.reactivex.Single

class Repository {
    private val service: APIService = RetroInstant.getRetroInstance()

    fun executeHomeApi(): Single<HomeResponse> {
        return service.getHomeList()
    }

    fun executeImagesApi(): Single<List<Images>> {
        return service.getImagesList()
    }
}