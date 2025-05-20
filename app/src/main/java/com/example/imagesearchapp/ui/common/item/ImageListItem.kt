package com.example.imagesearchapp.ui.common.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.search.item.LoadMoreItem
import com.example.imagesearchapp.util.IMAGE_CONTENT_TYPE
import com.example.imagesearchapp.util.IMAGE_GRID
import com.example.imagesearchapp.util.LOAD_MORE_ITEM_TYPE

@Composable
internal fun ImageListItem(
    listState: LazyStaggeredGridState,
    imageList: List<ImageUiModel>,
    isLoadMore: Boolean,
    onClickFavorite: (ImageUiModel) -> Unit,
){
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