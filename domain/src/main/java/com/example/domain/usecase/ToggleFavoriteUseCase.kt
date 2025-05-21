package com.example.domain.usecase

import com.example.domain.entity.FavoriteEntity
import com.example.domain.repository.FavoriteRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(
        isFavorite: Boolean,
        favoriteItem: FavoriteEntity
    ) {
        if (isFavorite) {
            favoriteRepository.likeItem(favoriteItem)
        } else {
            favoriteRepository.unLikeItem(favoriteItem)
        }
    }
}