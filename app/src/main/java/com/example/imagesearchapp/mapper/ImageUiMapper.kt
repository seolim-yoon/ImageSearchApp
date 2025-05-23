package com.example.imagesearchapp.mapper

import com.example.domain.entity.FavoriteEntity
import com.example.domain.entity.ImageEntity
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.util.dateFormatter
import javax.inject.Inject

class ImageUiMapper @Inject constructor() {
    fun mapToImageUiModelList(imageList: List<ImageEntity>): List<ImageUiModel> =
        imageList.map { image ->
            ImageUiModel(
                id = image.thumbnail + "_" + image.dateTime,
                type = image.type,
                thumbnail = image.thumbnail,
                dateTime = dateFormatter(image.dateTime),
                isFavorite = image.isFavorite
            )
        }

    fun mapToImageUiModel(favoriteImage: FavoriteEntity): ImageUiModel =
        ImageUiModel(
            id = favoriteImage.id,
            type = favoriteImage.type,
            thumbnail = favoriteImage.thumbnail,
            dateTime = favoriteImage.datetime,
            isFavorite = true
        )

    fun mapToFavoriteEntity(image: ImageUiModel): FavoriteEntity =
        FavoriteEntity(
            id = image.id,
            type = image.type,
            thumbnail = image.thumbnail,
            datetime = image.dateTime
        )
}