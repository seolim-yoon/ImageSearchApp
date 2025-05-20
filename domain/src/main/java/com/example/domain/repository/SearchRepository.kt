package com.example.domain.repository

import com.example.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchImage(keyword: String, page: Int, pageSize: Int): Flow<ImageEntity>

    suspend fun searchVideo(keyword: String, page: Int, pageSize: Int): Flow<ImageEntity>
}