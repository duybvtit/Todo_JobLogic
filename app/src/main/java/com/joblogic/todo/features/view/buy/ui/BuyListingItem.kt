package com.joblogic.todo.features.view.buy.ui

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
import com.joblogic.todo.domain.entities.product.ToBuyItem
import com.joblogic.todo.features.ui.custom.CustomText


@Composable
fun BuyListingItem(
    modifier: Modifier = Modifier,
    toBuyItem: ToBuyItem
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
                content = "Name: ${toBuyItem.name}",
                fontSize = 13.sp,
                color = Color.Black
            )

            CustomText(
                content = "Price: ${toBuyItem.price}",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )

            CustomText(
                content = "Quantity: ${toBuyItem.quantity}",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}