package com.example.imagesearchapp.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
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
    imageUiModel: ImageUiModel,
    onClickFavorite: () -> Unit
) {
    Box {
        AsyncImageItem(
            imgUrl = imageUiModel.thumbnail,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClickFavorite()
                }
        )

        Text(
            text = imageUiModel.dateTime,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(
                    color = Color.Black
                )
                .padding(4.dp)
        )

        Icon(
            imageVector = if (imageUiModel.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            tint = if (imageUiModel.isFavorite) Color.Red else Color.Black,
            modifier = Modifier
                .size(35.dp)
                .padding(4.dp)
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
                dateTime = LocalDateTime.now().toString(),
                isFavorite = false
            ),
            onClickFavorite = {}
        )
    }
}