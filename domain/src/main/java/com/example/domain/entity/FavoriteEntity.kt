package com.example.domain.entity

import com.example.domain.util.ImageType

data class FavoriteEntity(
    val id: String,
    val type: ImageType,
    val thumbnail: String,
    val datetime: String
)