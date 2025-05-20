package com.example.imagesearchapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.base.LoadState
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.screen.ErrorScreen
import com.example.imagesearchapp.util.IMAGE_CONTENT_TYPE
import com.example.imagesearchapp.util.IMAGE_GRID

@Composable
internal fun ImageListItem(
    loadState: LoadState,
    imageList: List<ImageUiModel>,
    isLoadMore: Boolean,
    onClickFavorite: (ImageUiModel) -> Unit,
    loadMoreItem: () -> Unit,
    onRefresh: () -> Unit
) {
    when (loadState) {
        is LoadState.Loading -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        is LoadState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(IMAGE_GRID),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
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