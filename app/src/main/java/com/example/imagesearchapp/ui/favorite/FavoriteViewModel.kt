package com.example.imagesearchapp.ui.favorite

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFavoriteUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import com.example.imagesearchapp.base.BaseViewModel
import com.example.imagesearchapp.mapper.ImageUiMapper
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiEvent
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val imageUiMapper: ImageUiMapper
) : BaseViewModel<FavoriteUiState, FavoriteUiEvent, Nothing>() {
    override fun createInitialState(): FavoriteUiState = FavoriteUiState()

    init {
        getFavoriteList()
    }

    private fun getFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteUseCase().collect { list ->
                setState {
                    copy(
                        favoriteList = list.map { image ->
                            imageUiMapper.mapToImageUiModel(image)
                        }
                    )
                }
            }
        }
    }

    private fun clickFavorite(imageUiModel: ImageUiModel) {
        val updatedList = currentState.favoriteList.map { image ->
            if (image.id == imageUiModel.id) {
                image.copy(isFavorite = !image.isFavorite)
            } else image
        }

        setState {
            copy(favoriteList = updatedList)
        }

        val updatedItem = updatedList.find { it.id == imageUiModel.id } ?: return

        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoriteUseCase(
                isFavorite = updatedItem.isFavorite,
                favoriteItem = imageUiMapper.mapToFavoriteEntity(updatedItem)
            )
        }
    }

    override fun onEvent(event: FavoriteUiEvent) {
        when (event) {
            is FavoriteUiEvent.FavoriteImage -> {
                clickFavorite(event.imageUiModel)
            }
        }
    }
}