package com.joblogic.todo.core.platform

import androidx.navigation.NamedNavArgument
import com.joblogic.todo.features.root.NavTarget
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

data class NavigationRouteData(
    val route: String = "",
    val isPopBackStack: Boolean = false,
    val popUpToRoute: String? = null,
    val navigateToBottomNavigationItem: String? = null
)

abstract class BaseNavigator {
    val mutableSharedFlow = MutableSharedFlow<NavigationRouteData>(extraBufferCapacity = 1)
    open var sharedFlow = mutableSharedFlow.asSharedFlow()

    abstract fun popBackStack()
    abstract fun getRoute(navTarget: NavTarget): String
    abstract fun getRouteSuffix(navTarget: NavTarget): String
    abstract fun getRouteWithArguments(
        navTarget: NavTarget,
        navArguments: List<NamedNavArgument>?,
    ): String

    abstract fun getArguments(navTarget: NavTarget): List<NamedNavArgument>
    abstract fun navigateTo(
        navTarget: NavTarget,
        navArguments: List<NamedNavArgument>? = null,
        popUpToRoute: String? = null
    )
}