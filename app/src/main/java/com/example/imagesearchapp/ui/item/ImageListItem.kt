package com.example.imagesearchapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.base.LoadState
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.screen.ErrorScreen
import com.example.imagesearchapp.util.IMAGE_CONTENT_TYPE
import com.example.imagesearchapp.util.IMAGE_GRID
import com.example.imagesearchapp.util.LOAD_MORE_ITEM_TYPE

@Composable
internal fun ImageListItem(
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
            LazyVerticalStaggeredGrid(
                state = listState,
                columns = StaggeredGridCells.Fixed(IMAGE_GRID),
                contentPadding = PaddingValues(4.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    key = { it.id },
                    contentType = { IMAGE_CONTENT_TYPE },
                    items = imageList
                ) { image ->
                    ImageItem(
                        imageUiModel = image,
                        onClickFavorite = { onClickFavorite(image) }
                    )
                }

                if (isLoadMore) {
                    item(
                        contentType = { LOAD_MORE_ITEM_TYPE }
                    ) {
                        LoadMoreItem()
                    }
                }
            }
        }

        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = loadState.error.message.toString(),
                onRefresh = onRefresh
            )
        }
    }
}