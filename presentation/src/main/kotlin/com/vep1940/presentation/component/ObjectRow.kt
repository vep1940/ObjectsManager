package com.vep1940.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vep1940.presentation.model.ObjectDisplay

@Composable
internal fun SelectableObjectRow(
    onClick: () -> Unit = {},
    item: ObjectDisplay,
    isSelected: Boolean,
) {
    ObjectRow(
        onClick = onClick,
        item = item,
        rightSlot = {
            if (isSelected) {
                Icon(
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = "selected"
                )
            }
        }
    )
}

@Composable
internal fun RemovableObjectRow(
    onClick: () -> Unit = {},
    onRemoveClick: () -> Unit = {},
    item: ObjectDisplay,
) {
    ObjectRow(
        onClick = onClick,
        item = item,
        rightSlot = {
            OutlinedButton(onClick = onRemoveClick) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_delete),
                    contentDescription = "delete"
                )
            }
        }
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ObjectRow(
    onClick: () -> Unit = {},
    rightSlot: @Composable () -> Unit = {},
    item: ObjectDisplay,
) {
    Card(
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text(
                    text = "${item.type}: ${item.name}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.basicMarquee()
                )
                Text(
                    text = item.description,
                    modifier = Modifier.basicMarquee()
                )
            }
            rightSlot()
        }
    }
}