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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun likeItem(item: Favorite)

    @Delete
    fun unLikeItem(item: Favorite)
}