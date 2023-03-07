@file:OptIn(ExperimentalMaterial3Api::class)

package com.joblogic.todo.features.view.call.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.joblogic.todo.features.view.call.viewmodel.CallViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CallListingScreen(
    appNavigator: AppNavigator,
    callViewModel: CallViewModel = hiltViewModel()
) {
    val toCallDataState by remember(callViewModel) {
        callViewModel.toCallDataState
    }.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }

    val isShowLoading = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        isShowLoading.value = true
        callViewModel.getCallListing()
    }

    LaunchedEffect(toCallDataState) {
        if (toCallDataState.items.isNotEmpty()) {
            isShowLoading.value = false
        }

        if (toCallDataState.error.isNotEmpty()) {
            isShowLoading.value = false

            //show error
            snackBarHostState.showSnackbar(toCallDataState.error)
        }

        callViewModel.resetState()
    }

    fun popBackStack() {
        appNavigator.popBackStack()
    }

    BackHandler {
        popBackStack()
    }

    Scaffold(
        snackbarHost = { androidx.compose.material3.SnackbarHost(snackBarHostState) },
        topBar = {
            IncludeTopAppBar(title = stringResource(id = R.string.call_list),
                onBackClick = { popBackStack() })
        },
    ) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(toCallDataState.items) {
                CallListingItem(callItem = it)
            }
        }
    }

    CustomCircularProgressIndicator(isShow = isShowLoading.value)
}