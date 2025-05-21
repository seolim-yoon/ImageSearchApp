package com.example.domain.usecase

import com.example.domain.entity.FavoriteEntity
import com.example.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<FavoriteEntity>> = favoriteRepository.getAllFavoriteItem()
}