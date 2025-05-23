package com.example.imagesearchapp.ui.search.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.example.imagesearchapp.base.LoadState
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.common.item.ImageListItem
import com.example.imagesearchapp.ui.search.screen.ErrorScreen

@Composable
internal fun SearchResultItem(
    listState: LazyStaggeredGridState,
    loadState: LoadState,
    imageList: List<ImageUiModel>,
    isLoadMore: Boolean,
    onClickFavorite: (ImageUiModel) -> Unit,
    loadMoreItem: () -> Unit,
    onRefresh: () -> Unit
) {
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        }.collect { lastVisibleIndex ->
            val totalCount = listState.layoutInfo.totalItemsCount
            if (totalCount > 0 && lastVisibleIndex >= totalCount - 1) {
                loadMoreItem()
            }
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