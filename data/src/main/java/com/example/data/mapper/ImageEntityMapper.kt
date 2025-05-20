package com.example.data.mapper

import com.example.data.dto.ImageDTO
import com.example.data.dto.VideoDTO
import com.example.domain.entity.ImageEntity
import javax.inject.Inject

class ImageEntityMapper @Inject constructor() {
    fun mapImageDocumentToImageEntity(image: ImageDTO.ImageDocument): ImageEntity =
        ImageEntity(
            id = image.thumbnailUrl + "_" + image.datetime,
            thumbnail = image.thumbnailUrl,
            dateTime = image.datetime
        )

    fun mapVideoDocumentToImageEntity(image: VideoDTO.VideoDocument): ImageEntity =
        ImageEntity(
            id = image.thumbnail + "_" + image.datetime,
            thumbnail = image.thumbnail,
            dateTime = image.datetime
        )
}