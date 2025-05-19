package com.example.imagesearchapp.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.model.ImageUiModel
import com.example.imagesearchapp.ui.theme.ImageSearchAppTheme
import java.time.LocalDateTime

@Composable
internal fun ImageItem(
    imageUiModel: ImageUiModel
) {
    Box {
        AsyncImageItem(
            imgUrl = imageUiModel.thumbnail
        )

        Text(
            text = imageUiModel.dateTime.toString(),
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
                .background(
                    color = Color.Black
                )
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchScreen() {
    ImageSearchAppTheme {
        ImageItem(
            imageUiModel = ImageUiModel(
                id = "",
                thumbnail = "",
                dateTime = LocalDateTime.now(),
                isFavorite = false
            )
        )
    }
}