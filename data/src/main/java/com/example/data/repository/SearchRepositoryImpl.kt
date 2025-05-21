package com.example.data.repository

import com.example.data.datasource.local.SearchLocalDataSource
import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data.mapper.ImageEntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val imageEntityMapper: ImageEntityMapper
) : SearchRepository {
    override fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity> =
        getCacheOrRemoteData(
            cacheKey = "image-$keyword",
            getRemoteData = {
                searchRemoteDataSource.searchImage(keyword = keyword, page = page, pageSize = pageSize).documents
            },
            mapper = imageEntityMapper::mapImageDocumentToImageEntity
        )

    override fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity> =
        getCacheOrRemoteData(
            cacheKey = "video-$keyword",
            getRemoteData = {
                searchRemoteDataSource.searchVideo(keyword = keyword, page = page, pageSize = pageSize).documents
            },
            mapper = imageEntityMapper::mapVideoDocumentToImageEntity
        )

    private fun <T> getCacheOrRemoteData(
        cacheKey: String,
        getRemoteData: suspend () -> List<T>,
        mapper: (T) -> ImageEntity
    ): Flow<ImageEntity> = flow {
        val cacheData = searchLocalDataSource.loadCacheData<T>(cacheKey)

        val emitData = cacheData?.data ?: run {
            searchLocalDataSource.saveCacheData(
                cacheKey = cacheKey,
                documents = getRemoteData()
            )
            getRemoteData()
        }

        emitData.forEach { emit(mapper(it)) }
    }
}
