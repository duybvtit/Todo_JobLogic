package com.joblogic.todo.features.view.call.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joblogic.todo.domain.entities.product.ToCallItem
import com.joblogic.todo.features.ui.custom.CustomText


@Composable
fun CallListingItem(
    modifier: Modifier = Modifier,
    callItem: ToCallItem
) {
    Card(
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CustomText(
                content = "Name: ${callItem.name}",
                fontSize = 13.sp,
                color = Color.Black
            )

            CustomText(
                content = "Phone Number: ${callItem.number}",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}