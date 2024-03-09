package com.krai29.youtubepaging3demo.api

import com.krai29.youtubepaging3demo.data.YoutubeData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun fetchApiData(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("order") order: String,
        @Query("maxResult") maxResult: String,
        @Query("type") type: String,
        @Query("pageToken") pageToken: String
    ): YoutubeData
}