package com.example.imagesearchapp.ui.search.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imagesearchapp.ui.search.contract.ImageUiEffect
import com.example.imagesearchapp.ui.search.contract.ImageUiEvent
import com.example.imagesearchapp.ui.search.contract.ImageUiState
import com.example.imagesearchapp.ui.search.item.SearchBarItem
import com.example.imagesearchapp.ui.search.item.SearchResultItem
import com.example.imagesearchapp.ui.theme.ImageSearchAppTheme
import com.example.imagesearchapp.util.DEFAULT_KEYWORD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun SearchScreen(
    state: ImageUiState,
    onEvent: (ImageUiEvent) -> Unit,
    effectFlow: Flow<ImageUiEffect>,
    inputKeyword: String,
) {
    val listState = rememberLazyStaggeredGridState()

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is ImageUiEffect.ScrollToTop -> {
                    listState.scrollToItem(0)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBarItem(
            inputText = inputKeyword,
            onValueChange = { keyword -> onEvent(ImageUiEvent.InputKeyword(keyword)) },
            onClickClearBtn = { onEvent(ImageUiEvent.InputKeyword(DEFAULT_KEYWORD)) }
        )

        SearchResultItem(
            listState = listState,
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
            effectFlow = emptyFlow(),
            inputKeyword = DEFAULT_KEYWORD
        )
    }
}