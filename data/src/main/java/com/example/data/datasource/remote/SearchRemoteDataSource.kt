package com.example.data.datasource.remote

import com.example.data.datasource.remote.api.SearchApi
import com.example.data.dto.ImageDTO
import com.example.data.dto.VideoDTO
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): ImageDTO = searchApi.searchImage(keyword = keyword, page = page, pageSize = pageSize)

    suspend fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): VideoDTO = searchApi.searchVideo(keyword = keyword, page = page, pageSize = pageSize)

}