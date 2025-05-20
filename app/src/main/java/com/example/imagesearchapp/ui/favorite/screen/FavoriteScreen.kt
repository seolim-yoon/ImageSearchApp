package com.example.imagesearchapp.ui.favorite.screen

import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import com.example.imagesearchapp.ui.common.item.ImageListItem
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiEvent
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiState

@Composable
internal fun FavoriteScreen(
    state: FavoriteUiState,
    onEvent: (FavoriteUiEvent) -> Unit,
) {
    ImageListItem(
        listState = rememberLazyStaggeredGridState(),
        imageList = state.favoriteList,
        isLoadMore = false,
        onClickFavorite = {  }
    )
}