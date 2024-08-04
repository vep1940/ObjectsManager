package com.vep1940.presentation.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vep1940.presentation.R
import com.vep1940.presentation.component.LoadingScreen
import com.vep1940.presentation.component.RemovableObjectRow
import com.vep1940.presentation.model.ObjectDisplay
import com.vep1940.presentation.modifier.clickableNoEffect
import com.vep1940.presentation.screen.list.model.ListScreenAction
import com.vep1940.presentation.screen.list.model.ListScreenDisplay
import com.vep1940.presentation.screen.list.model.ListScreenState

@Composable
internal fun ListScreen(
    screenState: ListScreenState,
    action: (ListScreenAction) -> Unit,
) {
    when (screenState) {
        ListScreenState.Error -> Error(action)
        ListScreenState.Loading -> LoadingScreen()
        is ListScreenState.Success -> Success(display = screenState.display, action)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Success(
    display: ListScreenDisplay,
    action: (ListScreenAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.list_screen_title)) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { action(ListScreenAction.AddObject) }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_add),
                    contentDescription = "FAB"
                )
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                SearchBar(
                    query = display.search,
                    onQueryChange = { searchText -> action(ListScreenAction.Search(searchText)) },
                    onSearch = { },
                    active = true,
                    onActiveChange = {},
                    placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_search),
                            contentDescription = "Icon to show that this is a search bar",
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_close),
                            contentDescription = "Icon to reset the search bar",
                            modifier = Modifier.clickableNoEffect { action(ListScreenAction.Search("")) },
                        )
                    },
                ) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(
                            items = display.items,
                            key = { item -> item.id },
                        ) { item ->
                            RemovableObjectRow(
                                onClick = { action(ListScreenAction.SelectObject(item.id)) },
                                onRemoveClick = { action(ListScreenAction.DeleteObject(item.id)) },
                                item = item
                            )
                        }
                    }
                }
            }

        }
    )
}

@Composable
private fun Error(action: (ListScreenAction) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(text = stringResource(id = R.string.list_screen_error_message))
            Button(onClick = { action(ListScreenAction.OkError) }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    }
}

@Preview
@Composable
private fun ListScreenPreview() {
    ListScreen(
        screenState = ListScreenState.Success(
            ListScreenDisplay(
                items = ListScreenPreviewData.items,
                search = "",
            )
        ),
        action = {},
    )
}

private object ListScreenPreviewData {
    val items = listOf(
        ObjectDisplay(
            id = 1,
            name = "Object 1",
            description = "Description 1",
            type = "Type 1",
        ),
        ObjectDisplay(
            id = 2,
            name = "Object 2",
            description = "Description 2",
            type = "Type 2",
        ),
        ObjectDisplay(
            id = 3,
            name = "Object 3",
            description = "Description 3",
            type = "Type 3",
        ),
    )
}