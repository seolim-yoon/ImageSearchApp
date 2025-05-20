package com.example.imagesearchapp.ui.favorite

import androidx.lifecycle.viewModelScope
import com.example.data.repository.FavoriteRepository
import com.example.imagesearchapp.base.BaseViewModel
import com.example.imagesearchapp.mapper.ImageUiMapper
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiEvent
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val imageUiMapper: ImageUiMapper
) : BaseViewModel<FavoriteUiState, FavoriteUiEvent, Nothing>() {
    override fun createInitialState(): FavoriteUiState = FavoriteUiState()

    init {
        getFavoriteList()
    }

    private fun getFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.getAllFavoriteItem().collect { list ->
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

    override fun onEvent(event: FavoriteUiEvent) {
        when(event) {
            is FavoriteUiEvent.LikeItem -> {

            }
            is FavoriteUiEvent.UnLikeItem -> {

            }
        }
    }
}