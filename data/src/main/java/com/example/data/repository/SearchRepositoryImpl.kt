package com.example.data.repository

import com.example.data.datasource.local.SearchLocalDataSource
import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data.mapper.ImageEntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import okio.IOException
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val imageEntityMapper: ImageEntityMapper
) : SearchRepository {
    override suspend fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): List<ImageEntity> =
        getCacheOrRemoteData(
            cacheKey = "image-$page-$keyword",
            getRemoteData = {
                searchRemoteDataSource.searchImage(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                ).documents
            },
            mapper = imageEntityMapper::mapImageDocumentToImageEntityList
        )

    override suspend fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): List<ImageEntity> =
        getCacheOrRemoteData(
            cacheKey = "video-$page-$keyword",
            getRemoteData = {
                searchRemoteDataSource.searchVideo(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                ).documents
            },
            mapper = imageEntityMapper::mapVideoDocumentToImageEntityList
        )

    private suspend fun <T> getCacheOrRemoteData(
        cacheKey: String,
        getRemoteData: suspend () -> List<T>,
        mapper: (List<T>) -> List<ImageEntity>
    ): List<ImageEntity> {
        val cacheData = searchLocalDataSource.loadCacheData<T>(cacheKey)

        val emitData: List<T> = cacheData?.data ?: run {
            try {
                val remoteData = getRemoteData()
                searchLocalDataSource.saveCacheData(
                    cacheKey = cacheKey,
                    documents = remoteData
                )
                remoteData
            } catch (e: Exception) {
                if (e is IOException) {
                    throw e
                } else {
                    emptyList()
                }
            }
        }
        return mapper(emitData)
    }
}
