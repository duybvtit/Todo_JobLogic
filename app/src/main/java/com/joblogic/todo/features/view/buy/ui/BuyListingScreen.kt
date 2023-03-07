@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.joblogic.todo.features.view.buy.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joblogic.todo.R
import com.joblogic.todo.features.root.AppNavigator
import com.joblogic.todo.features.ui.custom.CustomCircularProgressIndicator
import com.joblogic.todo.features.ui.custom.IncludeTopAppBar
import com.joblogic.todo.features.view.buy.viewmodel.BuyViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BuyListScreen(
    appNavigator: AppNavigator,
    buyViewModel: BuyViewModel = hiltViewModel()
) {

    val toBuyDataState by remember(buyViewModel) {
        buyViewModel.toBuyDataState
    }.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    val isShowLoading = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        isShowLoading.value = true
        buyViewModel.getBuyListing()
    }

    LaunchedEffect(toBuyDataState) {
        if (toBuyDataState.items.isNotEmpty()) {
            isShowLoading.value = false
        }

        if (toBuyDataState.error.isNotEmpty()) {
            isShowLoading.value = false

            //show message
            snackbarHostState.showSnackbar(toBuyDataState.error)
        }

        buyViewModel.resetState()
    }

    fun popBackStack() {
        appNavigator.popBackStack()
    }

    BackHandler {
        popBackStack()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            IncludeTopAppBar(title = stringResource(id = R.string.buy_list),
                onBackClick = { popBackStack() })
        }) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(toBuyDataState.items) {
                BuyListingItem(toBuyItem = it)
            }
        }
    }

    CustomCircularProgressIndicator(isShow = isShowLoading.value)
}