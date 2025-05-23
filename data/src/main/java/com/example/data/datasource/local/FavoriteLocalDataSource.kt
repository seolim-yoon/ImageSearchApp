package com.example.data.datasource.local

import android.util.Log
import com.example.data.datasource.local.database.Favorite
import com.example.data.datasource.local.database.FavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    fun getAllFavoriteItem(): Flow<List<Favorite>> = favoriteDao.getAllFavoriteItem()

    suspend fun getAllFavoriteItemIds(): List<String> = favoriteDao.getAllFavoriteItemIds()

    suspend fun likeItem(item: Favorite) = favoriteDao.likeItem(item)

    suspend fun unLikeItem(item: Favorite)  {
        Log.v("seolim", "item : " + item.id)
         favoriteDao.unLikeItem(item)
    }
}