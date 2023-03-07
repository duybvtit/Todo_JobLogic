package com.joblogic.todo.features.root

sealed class NavTarget(val route: String) {
    object CallListScreen : NavTarget(route = "CallListScreen")
    object BuyListScreen : NavTarget(route = "BuyListScreen")
    object SellListScreen : NavTarget(route = "SellListScreen")
    object HomeScreen: NavTarget(route = "HomeScreen")
}