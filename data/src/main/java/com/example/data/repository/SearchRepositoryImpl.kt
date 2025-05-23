package com.example.data.repository

import com.example.data.datasource.local.CachedResult
import com.example.data.datasource.local.SearchLocalDataSource
import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data.dto.ImageDTO
import com.example.data.dto.VideoDTO
import com.example.data.mapper.ImageEntityMapper
import com.example.domain.entity.ImageListEntity
import com.example.domain.repository.SearchRepository
import okio.IOException
import javax.inject.Inject

data class FetchResult<T>(
    val documents: List<T>,
    val isEnd: Boolean
)

class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val imageEntityMapper: ImageEntityMapper
) : SearchRepository {

    override suspend fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): ImageListEntity {
        val cacheKey = "image-$page-$keyword"
        val cacheData = searchLocalDataSource.loadCacheData<ImageDTO.ImageDocument>(cacheKey)

        val result = fetchData(
            cacheKey = cacheKey,
            cacheData = cacheData,
            fetchRemoteData = {
                searchRemoteDataSource.searchImage(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                ).run {
                    FetchResult(
                        documents = documents,
                        isEnd = meta.isEnd
                    )
                }
            }
        )

        return imageEntityMapper.mapImageDocumentToImageEntityList(
            imageList = result.documents,
            isEnd = result.isEnd
        )
    }

    override suspend fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): ImageListEntity  {
        val cacheKey = "video-$page-$keyword"
        val cacheData =  searchLocalDataSource.loadCacheData<VideoDTO.VideoDocument>(cacheKey)

        val result = fetchData(
            cacheKey = cacheKey,
            cacheData = cacheData,
            fetchRemoteData = {
                searchRemoteDataSource.searchVideo(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                ).run {
                    FetchResult(
                        documents = documents,
                        isEnd = meta.isEnd
                    )
                }
            }
        )

        return imageEntityMapper.mapVideoDocumentToImageEntityList(
            videoList = result.documents,
            isEnd = result.isEnd
        )
    }

    private suspend fun <T> fetchData(
        cacheKey: String,
        cacheData: CachedResult<T>?,
        fetchRemoteData: suspend () -> FetchResult<T>
    ): FetchResult<T> = if (cacheData != null) {
        FetchResult(
            documents = cacheData.data,
            isEnd = cacheData.isEnd
        )
    } else {
        try {
            val remoteData = fetchRemoteData()
            searchLocalDataSource.saveCacheData(
                cacheKey = cacheKey,
                documents = remoteData.documents,
                isEnd = remoteData.isEnd
            )
            remoteData
        } catch (e: Exception) {
            if (e is IOException) {
                throw e
            } else {
                FetchResult(
                    documents = emptyList(),
                    isEnd = true
                )
            }
        }
    }
}
