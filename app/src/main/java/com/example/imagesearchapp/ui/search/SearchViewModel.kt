package com.example.imagesearchapp.ui.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetImageListUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import com.example.imagesearchapp.base.BaseViewModel
import com.example.imagesearchapp.base.LoadState
import com.example.imagesearchapp.mapper.ImageUiMapper
import com.example.imagesearchapp.model.CurrentInfo
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.search.contract.ImageUiEffect
import com.example.imagesearchapp.ui.search.contract.ImageUiEvent
import com.example.imagesearchapp.ui.search.contract.ImageUiState
import com.example.imagesearchapp.util.DEFAULT_PAGE
import com.example.imagesearchapp.util.ERROR_PAGE
import com.example.imagesearchapp.util.PAGE_SIZE
import com.example.imagesearchapp.util.SEARCH_TIME_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val imageUiMapper: ImageUiMapper
) : BaseViewModel<ImageUiState, ImageUiEvent, ImageUiEffect>() {
    override fun createInitialState(): ImageUiState = ImageUiState()

    private val _currentKeywordAndPage: MutableStateFlow<CurrentInfo> =
        MutableStateFlow(CurrentInfo())
    val currentKeywordAndPage = _currentKeywordAndPage.asStateFlow()

    private var isLoadingPaging = true

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val searchResult = _currentKeywordAndPage
        .debounce(SEARCH_TIME_DELAY)
        .filter { it.keyword.isNotBlank() && it.page != ERROR_PAGE }
        .flatMapLatest { (keyword, page) ->
            if (page == DEFAULT_PAGE) {
                setState {
                    copy(
                        loadState = LoadState.Loading
                    )
                }
            }
            flow {
                emit(
                    getImageListUseCase(
                        keyword = keyword,
                        page = page,
                        pageSize = PAGE_SIZE
                    )
                )
            }.catch { e ->
                handleException(e)
            }
        }

    init {
        searchImageByKeyword()
    }

    private fun searchImageByKeyword() {
        viewModelScope.launch {
            searchResult
                .collect { result ->
                    setState {
                        copy(
                            loadState = LoadState.Success,
                            isLoadMore = false,
                            imageList = imageList.toMutableList().apply {
                                addAll(
                                    imageUiMapper.mapToImageUiModelList(result)
                                )
                            }.distinct()
                        )
                    }
                    isLoadingPaging = false
                }
        }
    }

    private fun loadMore() {
        Log.v("kakao.com", "loadMore : " + isLoadingPaging)
        if (isLoadingPaging) return

        isLoadingPaging = true

        setState {
            copy(
                isLoadMore = true
            )
        }

        _currentKeywordAndPage.update { (keyword, page) ->
            CurrentInfo(
                keyword = keyword,
                page = (page + 1)
            )
        }
    }

    private fun refresh() {
        _currentKeywordAndPage.update { (keyword, _) ->
            CurrentInfo(
                keyword = keyword,
                page = DEFAULT_PAGE
            )
        }
    }

    private fun inputSearchKeyword(keyword: String) {
        setState {
            copy(
                imageList = listOf()
            )
        }
        _currentKeywordAndPage.update {
            CurrentInfo(
                keyword = keyword,
                page = DEFAULT_PAGE
            )
        }
    }

    private fun clickFavorite(imageUiModel: ImageUiModel) {
        val updatedList = currentState.imageList.map { image ->
            if (image.id == imageUiModel.id) {
                image.copy(isFavorite = !image.isFavorite)
            } else image
        }

        setState {
            copy(imageList = updatedList)
        }

        val updatedItem = updatedList.find { it.id == imageUiModel.id } ?: return

        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoriteUseCase(
                isFavorite = updatedItem.isFavorite,
                favoriteItem = imageUiMapper.mapToFavoriteEntity(updatedItem)
            )
        }
    }

    private fun handleException(throwable: Throwable) {
        setState {
            copy(
                isLoadMore = false,
                loadState = LoadState.Error(throwable)
            )
        }
        isLoadingPaging = false

        _currentKeywordAndPage.update { (keyword, _) ->
            CurrentInfo(
                keyword = keyword,
                page = ERROR_PAGE
            )
        }
    }

    override fun onEvent(event: ImageUiEvent) {
        when (event) {
            is ImageUiEvent.LoadMore -> {
                loadMore()
            }

            is ImageUiEvent.Refresh -> {
                refresh()
            }

            is ImageUiEvent.InputKeyword -> {
                inputSearchKeyword(
                    keyword = event.keyword
                )
            }

            is ImageUiEvent.FavoriteImage -> {
                clickFavorite(event.imageUiModel)
            }
        }
    }
}