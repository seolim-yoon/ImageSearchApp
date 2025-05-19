package com.example.imagesearchapp.ui.contract

import com.example.imagesearchapp.base.LoadState
import com.example.imagesearchapp.base.UiState
import com.example.imagesearchapp.model.ImageUiModel

data class ImageUiState(
    val loadState: LoadState = LoadState.Success,
    val isLoadMore: Boolean = false,
    val imageList: List<ImageUiModel> = emptyList()
): UiState