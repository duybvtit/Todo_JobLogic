package com.joblogic.todo.features.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.joblogic.todo.R

@Composable
fun CustomCircularProgressIndicator(
    isShow: Boolean,
    modifier: Modifier = Modifier
) {
    if (isShow) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.black).copy(alpha = 0.2f))
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(8.dp),
                color = colorResource(id = R.color.purple_500),
                strokeWidth = 2.dp
            )
        }
    }
}