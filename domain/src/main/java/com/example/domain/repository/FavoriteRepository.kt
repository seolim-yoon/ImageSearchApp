package com.example.domain.repository

import com.example.domain.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
     fun getAllFavoriteItem(): Flow<List<FavoriteEntity>>

     suspend fun getAllFavoriteItemIds(): List<String>

     suspend fun likeItem(item: FavoriteEntity)

     suspend fun unLikeItem(item: FavoriteEntity)
}