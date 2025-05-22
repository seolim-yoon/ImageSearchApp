package com.example.data.datasource.remote.api

import com.example.data.dto.ImageDTO
import com.example.data.dto.VideoDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/image")
    suspend fun searchImage(
        @Query("query") keyword: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): ImageDTO

    @GET("search/vclip")
    suspend fun searchVideo(
        @Query("query") keyword: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): VideoDTO
}