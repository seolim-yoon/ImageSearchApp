package com.example.data.repository

import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data.mapper.ImageEntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val imageEntityMapper: ImageEntityMapper
) : SearchRepository {
    override suspend fun searchImageAndVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity> = channelFlow {
            launch {
                searchRemoteDataSource.searchImage(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                ).documents.forEach {
                    send(
                        imageEntityMapper.mapImageDocumentToImageEntity(
                            it
                        )
                    )
                }
            }
            launch {
                searchRemoteDataSource.searchVideo(
                    keyword = keyword,
                    page = page,
                    pageSize = pageSize
                ).documents.forEach {
                    send(
                        imageEntityMapper.mapVideoDocumentToImageEntity(
                            it
                        )
                    )
                }
            }
        }
}