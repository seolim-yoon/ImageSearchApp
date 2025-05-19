package com.example.imagesearchapp.model

import java.time.LocalDateTime

data class ImageUiModel(
    val thumbnail: String,
    val dateTime: LocalDateTime,
    val isFavorite: Boolean
)