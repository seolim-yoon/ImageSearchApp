package com.example.imagesearchapp.ui.favorite.contract

import com.example.imagesearchapp.base.UiState
import com.example.imagesearchapp.model.ImageUiModel

data class FavoriteUiState(
    val favoriteList: List<ImageUiModel> = listOf()
): UiState