package com.vep1940.presentation.screen.list

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vep1940.presentation.screen.list.model.ListScreenAction
import org.koin.androidx.compose.koinViewModel

const val LIST_SCREEN_ROUTE = "listScreen"

fun NavGraphBuilder.listScreen(
    navigateToDetailScreen: () -> Unit,
) {
    composable(LIST_SCREEN_ROUTE) {
        val viewModel: ListScreenViewModel = koinViewModel()
        val display = viewModel.display.collectAsStateWithLifecycle()
        val action = ListScreenAction(
            onClickAdd = {},
            onClickNavigate = navigateToDetailScreen,
        )
        ListScreen(
            display = display.value,
            action = action,
        )
    }
}

fun NavController.navigateToListScreen() {
    navigate(LIST_SCREEN_ROUTE)
}