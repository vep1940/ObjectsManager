package com.vep1940.presentation.screen.detail

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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vep1940.presentation.R
import com.vep1940.presentation.component.LoadingScreen
import com.vep1940.presentation.component.ReadOnlyTextField
import com.vep1940.presentation.component.RemovableObjectRow
import com.vep1940.presentation.screen.detail.model.DetailScreenAction
import com.vep1940.presentation.screen.detail.model.DetailScreenDisplay
import com.vep1940.presentation.screen.detail.model.DetailScreenState

@Composable
internal fun DetailScreen(
    screenState: DetailScreenState,
    action: (DetailScreenAction) -> Unit,
) {
    when (screenState) {
        DetailScreenState.Error -> Error(action)
        DetailScreenState.Loading -> LoadingScreen()
        is DetailScreenState.Success -> Success(display = screenState.display, action = action)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Success(display: DetailScreenDisplay, action: (DetailScreenAction) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.detail_screen_title)) },
                actions = {
                    Button(onClick = { action(DetailScreenAction.ModifyObject(display.item.id)) }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_edit),
                            contentDescription = "edit object"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { action(DetailScreenAction.AddRelation(display.item.id)) }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_add),
                    contentDescription = "FAB"
                )
            }
        },
        content = { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                ReadOnlyTextField(
                    value = display.item.type,
                    label = stringResource(id = R.string.type_text_input_label),
                )
                ReadOnlyTextField(
                    value = display.item.name,
                    label = stringResource(id = R.string.name_text_input_label),
                )
                ReadOnlyTextField(
                    value = display.item.description,
                    label = stringResource(id = R.string.description_text_input_label),
                )
                Text(
                    text = stringResource(id = R.string.detail_screen_relations_title),
                    fontWeight = FontWeight.Bold,
                )
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(
                        items = display.item.relations,
                        key = { item -> item.id },
                    ) { item ->
                        RemovableObjectRow(
                            onClick = {
                                action(
                                    DetailScreenAction.ModifyRelation(
                                        display.item.id,
                                        item.id
                                    )
                                )
                            },
                            onRemoveClick = {
                                action(
                                    DetailScreenAction.DeleteRelation(
                                        display.item.id,
                                        item.id
                                    )
                                )
                            },
                            item = item
                        )
                    }
                }
            }

        }
    )
}

@Composable
private fun Error(action: (DetailScreenAction) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(text = stringResource(id = R.string.detail_screen_error_message))
            Button(onClick = { action(DetailScreenAction.OkError) }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
}