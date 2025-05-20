package com.example.imagesearchapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.util.IMAGE_CONTENT_TYPE
import com.example.imagesearchapp.util.IMAGE_GRID

@Composable
internal fun ImageListItem(
    imageList: List<ImageUiModel>,
    onClickFavorite: (ImageUiModel) -> Unit
) {
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