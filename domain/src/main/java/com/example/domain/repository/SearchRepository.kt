package com.example.domain.repository

import com.example.domain.entity.ImageEntity

interface SearchRepository {
    suspend fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): List<ImageEntity>

    suspend fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): List<ImageEntity>
}