package com.joblogic.todo.features.root

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.joblogic.todo.features.ui.custom.enterTransition
import com.joblogic.todo.features.ui.custom.popEnterTransition
import com.joblogic.todo.features.view.buy.ui.BuyListScreen
import com.joblogic.todo.features.view.call.ui.CallListingScreen
import com.joblogic.todo.features.view.call.viewmodel.CallViewModel
import com.joblogic.todo.features.view.home.view.HomeScreen
import com.joblogic.todo.features.view.home.viewmodel.HomeViewModel
import com.joblogic.todo.features.view.sell.SellListScreen
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(FlowPreview::class)
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun AppNavigationComponent(
    startDestination: String,
    navigator: AppNavigator,
    homeViewModel: HomeViewModel = hiltViewModel(),
    callViewModel: CallViewModel = hiltViewModel()
) {
    val navController = rememberAnimatedNavController()

    val homeRoute = navigator.getRoute(NavTarget.HomeScreen)
    val callListRoute = navigator.getRoute(NavTarget.CallListScreen)
    val buyListRoute = navigator.getRoute(NavTarget.BuyListScreen)
    val sellListRoute = navigator.getRoute(NavTarget.SellListScreen)

    LaunchedEffect("AppNavigationComponent") {
        navigator.sharedFlow.onEach { routeData ->
            if (routeData.isPopBackStack) {
                if (routeData.route.isEmpty()) {
                    navController.popBackStack()
                } else {
                    navController.popBackStack(route = routeData.route, inclusive = false)
                }
            } else {
                navController.navigate(routeData.route) {
                    routeData.popUpToRoute?.let { popUpTo(it) }
                }
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = homeRoute,
            popEnterTransition = {
                popEnterTransition(
                    listOf(
                        callListRoute,
                        buyListRoute,
                        sellListRoute
                    )
                )
            }
        ) {
            HomeScreen(navigator = navigator)
        }

        composable(
            route = callListRoute,
            arguments = navigator.getArguments(NavTarget.CallListScreen),
            enterTransition = {
                enterTransition(listOf(homeRoute))
            },
            popEnterTransition = {
                popEnterTransition(
                    listOf(
                    )
                )
            }
        ) { _ ->
            CallListingScreen(appNavigator = navigator)
        }

        composable(
            route = buyListRoute,
            arguments = navigator.getArguments(NavTarget.BuyListScreen),
            enterTransition = {
                enterTransition(listOf(homeRoute))
            },
            popEnterTransition = {
                popEnterTransition(
                    listOf(
                    )
                )
            }
        ) { _ ->
            BuyListScreen(appNavigator = navigator)
        }

        composable(
            route = sellListRoute,
            arguments = navigator.getArguments(NavTarget.SellListScreen),
            enterTransition = {
                enterTransition(listOf(homeRoute))
            },
            popEnterTransition = {
                popEnterTransition(
                    listOf(
                    )
                )
            }
        ) { _ ->
            SellListScreen(appNavigator = navigator)
        }
    }
}