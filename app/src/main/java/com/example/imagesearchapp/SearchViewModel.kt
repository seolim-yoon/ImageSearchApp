package com.example.imagesearchapp

import com.example.imagesearchapp.base.BaseViewModel
import com.example.imagesearchapp.ui.contract.ImageUiEffect
import com.example.imagesearchapp.ui.contract.ImageUiEvent
import com.example.imagesearchapp.ui.contract.ImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() :
    BaseViewModel<ImageUiState, ImageUiEvent, ImageUiEffect>() {
    override fun createInitialState(): ImageUiState = ImageUiState()

    override fun onEvent(event: ImageUiEvent) {
        when (event) {
            is ImageUiEvent.LoadMore -> {

            }
            is ImageUiEvent.Refresh -> {

            }
            is ImageUiEvent.InputKeyword -> {

            }
            is ImageUiEvent.FavoriteImage -> {

            }
        }
    }
}