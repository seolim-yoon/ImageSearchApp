package com.example.domain.usecase

import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(
        keyword: String,
        page: Int,
        pageSize: Int
    ): List<ImageEntity> =
        coroutineScope {
            val imageList = async {
                searchRepository.searchImage(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                )
            }
            val videoList = async {
                searchRepository.searchVideo(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                )
            }
            val (images, videos) = awaitAll(imageList, videoList)
            return@coroutineScope images + videos
        }
}