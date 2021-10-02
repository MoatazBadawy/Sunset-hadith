package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.APIService
import com.moataz.afternoonhadeeth.data.model.HomeResponse
import com.moataz.afternoonhadeeth.data.request.RetroInstant
import io.reactivex.Single

class Repository {

    private val service: APIService = RetroInstant.getRetroInstance()

    fun executeHomePageApi(): Single<HomeResponse?>? {
        return service.getObjectsList()
    }
}