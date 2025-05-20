package com.example.imagesearchapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.R
import com.example.imagesearchapp.ui.theme.ImageSearchAppTheme

@Composable
internal fun ErrorScreen(
    errorMessage: String,
    onRefresh:() -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = errorMessage,
            modifier = Modifier.padding(15.dp)
        )
        Button(
            onClick = onRefresh
        ) {
            Text(
                text = stringResource(R.string.refresh)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewErrorScreen() {
    ImageSearchAppTheme {
        ErrorScreen(
            errorMessage = "에러 발생!",
            onRefresh = {}
        )
    }
}