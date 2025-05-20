package com.example.domain.entity

data class ImageListEntity(
    val imageList: List<ImageEntity>,
    val isEnd: Boolean
)

data class ImageEntity(
    val id: String,
    val thumbnail: String,
    val dateTime: String
)