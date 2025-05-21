package com.example.imagesearchapp.ui.favorite.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.R
import com.example.imagesearchapp.ui.common.item.ImageListItem
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiEvent
import com.example.imagesearchapp.ui.favorite.contract.FavoriteUiState

@Composable
internal fun FavoriteScreen(
    state: FavoriteUiState,
    onEvent: (FavoriteUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.favorite_list),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        ImageListItem(
            listState = rememberLazyStaggeredGridState(),
            imageList = state.favoriteList,
            isLoadMore = false,
            onClickFavorite = { onEvent(FavoriteUiEvent.FavoriteImage(it)) }
        )
    }


}