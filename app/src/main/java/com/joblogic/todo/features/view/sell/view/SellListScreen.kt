@file:OptIn(ExperimentalMaterial3Api::class)

package com.joblogic.todo.features.view.sell

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joblogic.todo.R
import com.joblogic.todo.data.mappers.toBuyItem
import com.joblogic.todo.features.root.AppNavigator
import com.joblogic.todo.features.ui.custom.IncludeTopAppBar
import com.joblogic.todo.features.view.buy.ui.BuyListingItem
import com.joblogic.todo.features.view.sell.viewmodel.SellListScreenViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SellListScreen(
    appNavigator: AppNavigator,
    sellListScreenViewModel: SellListScreenViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    fun popBackStack() {
        appNavigator.popBackStack()
    }

    BackHandler {
        popBackStack()
    }

    val toSellDataState by remember(sellListScreenViewModel) {
        sellListScreenViewModel.toSellDataState
    }.collectAsStateWithLifecycle()

    LaunchedEffect(toSellDataState) {
        if (toSellDataState.items.isEmpty() && toSellDataState.isFetchingLocal) {
            snackbarHostState.showSnackbar("List is empty, access to call list first")

            sellListScreenViewModel.resetState()
        }
    }

    LaunchedEffect(true) {
        sellListScreenViewModel.getItemToSell()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            IncludeTopAppBar(title = stringResource(id = R.string.sell_list),
                onBackClick = { popBackStack() })
        }) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(toSellDataState.items) {
                BuyListingItem(toBuyItem = it.toBuyItem())
            }
        }
    }
}