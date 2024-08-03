package com.vep1940.presentation.screen.detail

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val DETAIL_SCREEN_ROUTE = "detailScreen"

fun NavGraphBuilder.detailScreen() {
    composable(route = DETAIL_SCREEN_ROUTE){
        val viewModel: DetailScreenViewModel = koinViewModel()
        val display = viewModel.display.collectAsStateWithLifecycle()

        DetailScreen(
            display = display.value,
            action = { action ->
                viewModel.onAction(action)
            },
        )
    }
}

fun NavController.navigateToDetailScreen() {
    navigate(DETAIL_SCREEN_ROUTE)
}