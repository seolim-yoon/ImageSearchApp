package com.example.imagesearchapp.model

import java.time.LocalDateTime

data class ImageUiModel(
    val id: String,
    val thumbnail: String,
    val dateTime: String,
    val isFavorite: Boolean
)