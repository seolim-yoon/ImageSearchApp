package com.example.imagesearchapp.mapper

import com.example.data.datasource.local.database.Favorite
import com.example.domain.entity.ImageEntity
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.util.dateFormatter
import javax.inject.Inject

class ImageUiMapper @Inject constructor() {
    fun mapToImageUiModel(image: ImageEntity): ImageUiModel =
        ImageUiModel(
            id = image.thumbnail + "_" + image.dateTime,
            thumbnail = image.thumbnail,
            dateTime = dateFormatter(image.dateTime),
            isFavorite = false
        )

    fun mapToImageUiModel(favoriteImage: Favorite): ImageUiModel =
        ImageUiModel(
            id = favoriteImage.id.toString(),
            thumbnail = favoriteImage.thumbnail,
            dateTime = favoriteImage.datetime,
            isFavorite = true
        )

    fun mapToFavorite(image: ImageUiModel): Favorite =
        Favorite(
            thumbnail = image.thumbnail,
            datetime = image.dateTime
        )
}