package com.example.domain.entity

import com.example.domain.util.ImageType

data class ImageEntity(
    val id: String,
    val type: ImageType,
    val thumbnail: String,
    val dateTime: String,
    val isFavorite: Boolean = false
)