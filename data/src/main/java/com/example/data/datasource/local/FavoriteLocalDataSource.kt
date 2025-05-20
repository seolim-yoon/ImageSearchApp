package com.example.data.datasource.local

import com.example.data.datasource.local.database.Favorite
import com.example.data.datasource.local.database.FavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    suspend fun getAllFavoriteItem(): Flow<List<Favorite>> = favoriteDao.getAllFavoriteItem()

    suspend fun likeItem(item: Favorite) = favoriteDao.likeItem(item)

    suspend fun unLikeItem(item: Favorite) = favoriteDao.unLikeItem(item)
}