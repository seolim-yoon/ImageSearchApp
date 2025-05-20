package com.example.data.datasource.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_id")
    var id: Int = 0,
    val thumbnail: String,
    val datetime: String
)
