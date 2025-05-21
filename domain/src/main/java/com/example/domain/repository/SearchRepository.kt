package com.example.domain.repository

import com.example.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity>

    fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity>
}