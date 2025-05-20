package com.example.imagesearchapp.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.imagesearchapp.R

enum class BottomNavType(
    val route: String,
    @StringRes val bottomTitleRes: Int,
    val icon: ImageVector
) {
    SEARCH(
        route = "Search",
        bottomTitleRes = R.string.search,
        icon = Icons.Default.Search
    ),

    FAVORITE(
        route = "Favorite",
        bottomTitleRes = R.string.favorite,
        icon = Icons.Default.Favorite
    );

    companion object {
        fun getBottomNavType(route: String): BottomNavType? =
            entries.find { route.contains(it.route) }
    }
}