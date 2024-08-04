package com.vep1940.presentation.screen.relationform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vep1940.presentation.R
import com.vep1940.presentation.component.LoadingScreen
import com.vep1940.presentation.component.SelectableObjectRow
import com.vep1940.presentation.screen.relationform.model.RelationFormAction
import com.vep1940.presentation.screen.relationform.model.RelationFormDisplay
import com.vep1940.presentation.screen.relationform.model.RelationFormState

@Composable
internal fun RelationForm(
    screenState: RelationFormState,
    action: (RelationFormAction) -> Unit,
) {
    when (screenState) {
        RelationFormState.Loading -> LoadingScreen()
        is RelationFormState.Success -> Success(display = screenState.display, action = action)
    }
}

@Composable
private fun Success(display: RelationFormDisplay, action: (RelationFormAction) -> Unit) {

    val height = (LocalConfiguration.current.screenHeightDp * 0.7).dp

    Card(
        modifier = Modifier.heightIn(max = height)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(horizontal = 16.dp),
        ) {
            if (display.possibleRelations.isEmpty()) {
                Text(text = stringResource(id = R.string.relation_form_no_possible_relations))
                Button(
                    onClick = { action(RelationFormAction.SaveRelation) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = stringResource(id = R.string.cancel_config))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = display.possibleRelations,
                        key = { item -> item.id },
                    ) { item ->
                        SelectableObjectRow(
                            onClick = { action(RelationFormAction.RelationChange(item.id)) },
                            item = item,
                            isSelected = item == display.selectedRelation,
                        )
                    }
                }
                Button(
                    onClick = { action(RelationFormAction.SaveRelation) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = stringResource(id = R.string.save_config))
                }
            }
        }
    }


}