package com.example.imagesearchapp.ui.search.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.imagesearchapp.base.LoadState
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.common.item.ImageListItem
import com.example.imagesearchapp.ui.search.screen.ErrorScreen

@Composable
internal fun SearchResultItem(
    loadState: LoadState,
    imageList: List<ImageUiModel>,
    isLoadMore: Boolean,
    onClickFavorite: (ImageUiModel) -> Unit,
    loadMoreItem: () -> Unit,
    onRefresh: () -> Unit
) {
    val listState = rememberLazyStaggeredGridState()
    val needLoadMore by remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex != 0 && lastVisibleItemIndex >= totalItemsCount - 2
        }
    }

    LaunchedEffect(needLoadMore) {
        if (needLoadMore) {
            loadMoreItem()
        }
    }

    when (loadState) {
        is LoadState.Loading -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        is LoadState.Success -> {
            ImageListItem(
                listState = listState,
                imageList = imageList,
                isLoadMore = isLoadMore,
                onClickFavorite = onClickFavorite
            )
        }

        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = loadState.error.message.toString(),
                onRefresh = onRefresh
            )
        }
    }
}