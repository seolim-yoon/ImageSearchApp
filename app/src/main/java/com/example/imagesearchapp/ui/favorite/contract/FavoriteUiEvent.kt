package com.example.imagesearchapp.ui.favorite.contract

import com.example.imagesearchapp.base.UiEvent
import com.example.imagesearchapp.model.ImageUiModel

sealed interface FavoriteUiEvent: UiEvent {
    data class LikeItem(
        val imageUiModel: ImageUiModel
    ): FavoriteUiEvent

    data class UnLikeItem(
        val imageUiModel: ImageUiModel
    ): FavoriteUiEvent
}