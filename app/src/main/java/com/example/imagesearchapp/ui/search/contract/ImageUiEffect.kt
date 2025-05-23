package com.example.imagesearchapp.ui.search.contract

import com.example.imagesearchapp.base.UiEffect

sealed interface ImageUiEffect: UiEffect {
    data object ScrollToTop: ImageUiEffect
}