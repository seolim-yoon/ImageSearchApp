package com.example.domain.usecase

import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity> {
        return merge(
            searchRepository.searchImage(
                keyword = keyword,
                page = page,
                pageSize = pageSize
            ), searchRepository.searchVideo(
                keyword = keyword,
                page = page,
                pageSize = pageSize
            )
        )
    }
}