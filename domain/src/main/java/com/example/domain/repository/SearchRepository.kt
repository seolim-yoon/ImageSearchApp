package com.example.domain.repository

import com.example.domain.entity.ImageListEntity

interface SearchRepository {
    suspend fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): ImageListEntity

    suspend fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): ImageListEntity
}