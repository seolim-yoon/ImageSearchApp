package com.example.data.mapper

import com.example.data.datasource.local.database.Favorite
import com.example.data.dto.ImageDTO
import com.example.data.dto.VideoDTO
import com.example.domain.entity.FavoriteEntity
import com.example.domain.entity.ImageEntity
import com.example.domain.entity.ImageListEntity
import com.example.domain.util.ImageType
import javax.inject.Inject

class ImageEntityMapper @Inject constructor() {
    fun mapImageDocumentToImageEntityList(
        imageList: List<ImageDTO.ImageDocument>,
        isEnd: Boolean
    ): ImageListEntity =
        ImageListEntity(
            imageList = imageList.map { image ->
                ImageEntity(
                    id = image.thumbnailUrl + "_" + image.datetime,
                    type = ImageType.IMAGE,
                    thumbnail = image.thumbnailUrl,
                    dateTime = image.datetime
                )
            },
            isEnd = isEnd
        )

    fun mapVideoDocumentToImageEntityList(
        videoList: List<VideoDTO.VideoDocument>,
        isEnd: Boolean
    ): ImageListEntity =
        ImageListEntity(
            imageList = videoList.map { video ->
                ImageEntity(
                    id = video.thumbnail + "_" + video.datetime,
                    type = ImageType.VIDEO,
                    thumbnail = video.thumbnail,
                    dateTime = video.datetime
                )
            },
            isEnd = isEnd
        )

    fun mapToFavoriteEntityList(favoriteList: List<Favorite>): List<FavoriteEntity> =
        favoriteList.map { favorite ->
            FavoriteEntity(
                id = favorite.id,
                type = ImageType.fromValue(favorite.type),
                thumbnail = favorite.thumbnail,
                datetime = favorite.datetime
            )
        }

    fun mapToFavorite(favorite: FavoriteEntity): Favorite =
        Favorite(
            id = favorite.id,
            type = favorite.type.name,
            thumbnail = favorite.thumbnail,
            datetime = favorite.datetime
        )
}