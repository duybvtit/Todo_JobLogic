package com.joblogic.todo.features.root

import androidx.navigation.NamedNavArgument
import com.joblogic.todo.core.platform.BaseNavigator
import com.joblogic.todo.core.platform.NavigationRouteData
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class AppNavigator @Inject constructor(
) : BaseNavigator() {
    override var sharedFlow = mutableSharedFlow.asSharedFlow()
    override fun popBackStack() {
        mutableSharedFlow.tryEmit(NavigationRouteData(isPopBackStack = true))
    }

    fun goToCallList() {
        navigateTo(
            navTarget = NavTarget.CallListScreen,
            navArguments = listOf()
        )
    }

    fun goToBuyList() {
        navigateTo(
            navTarget = NavTarget.BuyListScreen,
            navArguments = listOf()
        )
    }

    fun goToSellList() {
        navigateTo(
            navTarget = NavTarget.SellListScreen,
            navArguments = listOf()
        )
    }

    override fun getRoute(navTarget: NavTarget): String {
        return navTarget.route + getRouteSuffix(navTarget)
    }

    override fun getRouteSuffix(navTarget: NavTarget): String {
        return when (navTarget) {
            NavTarget.BuyListScreen -> {
                ""
            }

            NavTarget.CallListScreen -> {
                ""
            }

            NavTarget.SellListScreen -> {
                ""
            }

            else -> ""
        }
    }

    override fun getRouteWithArguments(
        navTarget: NavTarget,
        navArguments: List<NamedNavArgument>?
    ): String {
        return when (navTarget) {
            is NavTarget.CallListScreen, NavTarget.SellListScreen, NavTarget.BuyListScreen -> {
                navTarget.route
            }

            else -> navTarget.route
        }
    }

    override fun getArguments(navTarget: NavTarget): List<NamedNavArgument> {
        return when (navTarget) {
            is NavTarget.BuyListScreen, NavTarget.SellListScreen, NavTarget.CallListScreen -> listOf()
            else -> listOf()
        }
    }

    override fun navigateTo(
        navTarget: NavTarget,
        navArguments: List<NamedNavArgument>?,
        popUpToRoute: String?,
    ) {
        mutableSharedFlow.tryEmit(
            NavigationRouteData(
                route = getRouteWithArguments(navTarget, navArguments), popUpToRoute = popUpToRoute
            )
        )
    }
}