package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.HomeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {
    @GET("")
    fun getObjectsList(): Single<HomeResponse?>?
}