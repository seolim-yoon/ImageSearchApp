package com.example.domain.repository

import com.example.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchImageAndVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity>
}