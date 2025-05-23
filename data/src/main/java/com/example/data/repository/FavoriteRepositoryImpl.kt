package com.example.data.repository

import com.example.data.datasource.local.FavoriteLocalDataSource
import com.example.data.mapper.ImageEntityMapper
import com.example.domain.entity.FavoriteEntity
import com.example.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
    private val imageEntityMapper: ImageEntityMapper
) : FavoriteRepository {
    override fun getAllFavoriteItem(): Flow<List<FavoriteEntity>> =
        favoriteLocalDataSource.getAllFavoriteItem().map { itemList ->
            imageEntityMapper.mapToFavoriteEntityList(itemList)
        }

    override suspend fun getAllFavoriteItemIds(): List<String> =
        favoriteLocalDataSource.getAllFavoriteItemIds()

    override suspend fun likeItem(item: FavoriteEntity) =
        favoriteLocalDataSource.likeItem(imageEntityMapper.mapToFavorite(item))

    override suspend fun unLikeItem(item: FavoriteEntity) =
        favoriteLocalDataSource.unLikeItem(imageEntityMapper.mapToFavorite(item))
}