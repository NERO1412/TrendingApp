package com.lbird.trendingapp.api

import com.lbird.trendingapp.data.Trending
import retrofit2.http.GET

interface TrendingApi {

    companion object {
        const val BASE_URL = "https://gh-trending-api.herokuapp.com/"
    }

    @GET("repositories")
    suspend fun getTrendings(): List<Trending>
}