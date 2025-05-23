package com.example.data.datasource.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    fun getAllFavoriteItem(): Flow<List<Favorite>>

    @Query("SELECT favorite_id FROM Favorite")
    suspend fun getAllFavoriteItemIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeItem(item: Favorite)

    @Delete
    suspend fun unLikeItem(item: Favorite)
}