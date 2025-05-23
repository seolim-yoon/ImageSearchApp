package com.example.data.datasource.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "favorite_id")
    var id: String = "",
    val type: String,
    val thumbnail: String,
    val datetime: String
)
