package com.example.imagesearchapp.ui.favorite.contract

import com.example.imagesearchapp.base.UiEvent
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.search.contract.ImageUiEvent

sealed interface FavoriteUiEvent: UiEvent {
    data class FavoriteImage(
        val imageUiModel: ImageUiModel
    ): FavoriteUiEvent
}