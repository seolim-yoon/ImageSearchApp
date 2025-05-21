package com.example.data.mapper

import com.example.data.datasource.local.database.Favorite
import com.example.data.dto.ImageDTO
import com.example.data.dto.VideoDTO
import com.example.domain.entity.FavoriteEntity
import com.example.domain.entity.ImageEntity
import javax.inject.Inject

class ImageEntityMapper @Inject constructor() {
    fun mapImageDocumentToImageEntity(image: ImageDTO.ImageDocument): ImageEntity =
        ImageEntity(
            id = image.thumbnailUrl + "_" + image.datetime,
            thumbnail = image.thumbnailUrl,
            dateTime = image.datetime
        )

    fun mapVideoDocumentToImageEntity(video: VideoDTO.VideoDocument): ImageEntity =
        ImageEntity(
            id = video.thumbnail + "_" + video.datetime,
            thumbnail = video.thumbnail,
            dateTime = video.datetime
        )

    fun mapToFavoriteEntityList(favoriteList: List<Favorite>): List<FavoriteEntity> =
        favoriteList.map { favorite ->
            FavoriteEntity(
                id = favorite.id,
                thumbnail = favorite.thumbnail,
                datetime = favorite.datetime
            )
        }

    fun mapToFavorite(favorite: FavoriteEntity): Favorite =
        Favorite(
            id = favorite.id,
            thumbnail = favorite.thumbnail,
            datetime = favorite.datetime
        )
}