package com.example.imagesearchapp.model

import com.example.domain.util.ImageType

data class ImageUiModel(
    val id: String,
    val type: ImageType,
    val thumbnail: String,
    val dateTime: String,
    val isFavorite: Boolean
)