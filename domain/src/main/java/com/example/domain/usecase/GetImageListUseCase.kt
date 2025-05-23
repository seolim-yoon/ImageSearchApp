package com.example.domain.usecase

import com.example.domain.entity.ImageListEntity
import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(
        keyword: String,
        page: Int,
        pageSize: Int
    ): ImageListEntity =
        coroutineScope {
            val imageDeferred = async {
                    searchRepository.searchImage(
                        keyword = keyword,
                        page = page,
                        pageSize = pageSize
                    )
                }
            val videoDeferred = async {
                    searchRepository.searchVideo(
                        keyword = keyword,
                        page = page,
                        pageSize = pageSize
                    )
                }

            val favoriteIdDeferred = async {
                favoriteRepository.getAllFavoriteItemIds()
            }

            val (images, videos) = awaitAll(imageDeferred, videoDeferred)
            val favoriteIds = favoriteIdDeferred.await()

            val totalImageList = images.imageList + videos.imageList
                .sortedByDescending { it.dateTime }
                .map { entity ->
                    entity.copy(
                        isFavorite = favoriteIds.contains(entity.id)
                    )
                }

            return@coroutineScope ImageListEntity(
                imageList = totalImageList,
                isEnd = images.isEnd && videos.isEnd
            )
        }
}