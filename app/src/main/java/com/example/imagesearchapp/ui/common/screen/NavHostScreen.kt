package com.example.imagesearchapp.ui.common.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.imagesearchapp.ui.favorite.FavoriteViewModel
import com.example.imagesearchapp.ui.search.SearchViewModel
import com.example.imagesearchapp.ui.favorite.screen.FavoriteScreen
import com.example.imagesearchapp.ui.search.screen.SearchScreen
import com.example.imagesearchapp.util.BottomNavType

@Composable
internal fun NavHostScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavType.SEARCH.route,
        modifier = modifier
    ) {
        composable(
            route = BottomNavType.SEARCH.route
        ) {
            val viewModel: SearchViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val keywordAndPage by viewModel.currentKeywordAndPage.collectAsStateWithLifecycle()

            SearchScreen(
                state = state,
                onEvent = viewModel::onEvent,
                inputKeyword = keywordAndPage.keyword
            )
        }

        composable(
            route = BottomNavType.FAVORITE.route
        ) {
            val viewModel: FavoriteViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            FavoriteScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}