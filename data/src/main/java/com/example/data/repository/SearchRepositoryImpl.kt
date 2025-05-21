package com.example.data.repository

import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data.mapper.ImageEntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val imageEntityMapper: ImageEntityMapper
) : SearchRepository {
    override fun searchImage(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity> = flow {
        searchRemoteDataSource.searchImage(
            keyword = keyword,
            page = page,
            pageSize = pageSize
        ).documents.forEach {
            emit(
                imageEntityMapper.mapImageDocumentToImageEntity(it)
            )
        }
    }

    override fun searchVideo(
        keyword: String,
        page: Int,
        pageSize: Int
    ): Flow<ImageEntity> = flow {
        searchRemoteDataSource.searchVideo(
            keyword = keyword,
            page = page,
            pageSize = pageSize
        ).documents.forEach {
            emit(
                imageEntityMapper.mapVideoDocumentToImageEntity(it)
            )
        }
    }
}