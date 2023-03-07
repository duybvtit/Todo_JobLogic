package com.joblogic.todo.features.ui.custom

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joblogic.todo.R

@Composable
fun IncludeTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.black),
                fontSize = 13.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick.invoke()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_custom_back),
                    "",
                    tint = colorResource(id = R.color.black)
                )
            }
        },
        backgroundColor = colorResource(id = R.color.white),
        elevation = 0.dp
    )
}