package com.joblogic.todo.features.view.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joblogic.todo.R
import com.joblogic.todo.features.root.AppNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigator: AppNavigator) {

    fun goToCallList() {
        navigator.goToCallList()
    }

    fun goToBuyList() {
        navigator.goToBuyList()
    }

    fun goToSellList() {
        navigator.goToSellList()
    }

    Scaffold(modifier = Modifier.background(colorResource(id = R.color.black))) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.weight(0.33f)) {
                PrimaryButton(
                    text = stringResource(id = R.string.call_list),
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    onButtonClick = {
                        goToCallList()
                    }
                )
            }

            Box(modifier = Modifier.weight(0.33f)) {
                PrimaryButton(
                    text = stringResource(id = R.string.buy_list),
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    onButtonClick = {
                        goToBuyList()
                    }
                )
            }

            Box(modifier = Modifier.weight(0.33f)) {
                PrimaryButton(
                    text = stringResource(id = R.string.sell_list),
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    onButtonClick = {
                        goToSellList()
                    }
                )
            }

        }
    }
}

@Composable
private fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = { onButtonClick.invoke() },
        modifier = modifier
    ) {
        Text(text = text)
    }
}