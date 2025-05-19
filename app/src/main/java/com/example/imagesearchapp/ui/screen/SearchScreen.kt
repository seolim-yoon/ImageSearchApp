package com.example.imagesearchapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imagesearchapp.ui.contract.ImageUiEvent
import com.example.imagesearchapp.ui.contract.ImageUiState
import com.example.imagesearchapp.ui.item.ImageListItem
import com.example.imagesearchapp.ui.item.SearchBarItem
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

        ImageListItem(
            imageList = state.imageList
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