package com.example.imagesearchapp.ui.contract

import com.example.imagesearchapp.base.UiEvent
import com.example.imagesearchapp.model.ImageUiModel

sealed interface ImageUiEvent: UiEvent {
    data object LoadMore: ImageUiEvent
    data object Refresh: ImageUiEvent
    data class InputKeyword(
        val keyword: String
    ): ImageUiEvent
    data class FavoriteImage(
        val imageUiModel: ImageUiModel
    ): ImageUiEvent
}