package com.example.data.repository

import com.example.data.datasource.local.FavoriteLocalDataSource
import com.example.data.datasource.local.database.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource
) {
    suspend fun getAllFavoriteItem(): Flow<List<Favorite>> = favoriteLocalDataSource.getAllFavoriteItem()

    suspend fun likeItem(item: Favorite) = favoriteLocalDataSource.likeItem(item)

    suspend fun unLikeItem(item: Favorite) = favoriteLocalDataSource.unLikeItem(item)
}