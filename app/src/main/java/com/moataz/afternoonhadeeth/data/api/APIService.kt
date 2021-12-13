package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.model.image.Images
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {
    @GET("a58c790af5723a42493c")
    fun getHomeList(): Single<HomeResponse>

    @GET("8eff9e2d29547d671b88")
    fun getImagesList(): Single <List<Images>>
}