package com.example.imagesearchapp.ui.search.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.R

@Composable
internal fun SearchBarItem(
    inputText: String,
    onValueChange: (String) -> Unit,
    onClickClearBtn: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = onValueChange,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.search_place_holder)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = onClickClearBtn
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchBarItem() {
    SearchBarItem(
        inputText = "",
        onValueChange = { },
        onClickClearBtn = { }
    )
}

