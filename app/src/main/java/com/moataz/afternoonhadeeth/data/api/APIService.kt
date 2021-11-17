package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.model.image.Images
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {
    @GET("homepagedata.json")
    fun getHomeList(): Single<HomeResponse>
}