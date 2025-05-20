package com.example.imagesearchapp.ui.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imagesearchapp.ui.search.contract.ImageUiEvent
import com.example.imagesearchapp.ui.search.contract.ImageUiState
import com.example.imagesearchapp.ui.search.item.SearchBarItem
import com.example.imagesearchapp.ui.search.item.SearchResultItem
import com.example.imagesearchapp.ui.theme.ImageSearchAppTheme

@Composable
internal fun SearchScreen(
    state: ImageUiState,
    onEvent: (ImageUiEvent) -> Unit,
    inputKeyword: String,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBarItem(
            inputText = inputKeyword,
            onValueChange = { keyword -> onEvent(ImageUiEvent.InputKeyword(keyword)) },
            onClickClearBtn = { onEvent(ImageUiEvent.InputKeyword("")) }
        )

        SearchResultItem(
            loadState = state.loadState,
            imageList = state.imageList,
            isLoadMore = state.isLoadMore,
            onClickFavorite = { onEvent(ImageUiEvent.FavoriteImage(it)) },
            loadMoreItem = { onEvent(ImageUiEvent.LoadMore) },
            onRefresh = { onEvent(ImageUiEvent.Refresh) }
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewSearchScreen() {
    ImageSearchAppTheme {
        SearchScreen(
            state = ImageUiState(),
            onEvent = {},
            inputKeyword = ""
        )
    }
}