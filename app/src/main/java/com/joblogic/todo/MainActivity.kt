package com.joblogic.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.joblogic.todo.features.root.AppNavigationComponent
import com.joblogic.todo.features.root.AppNavigator
import com.joblogic.todo.features.root.NavTarget
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigationComponent(
                startDestination = appNavigator.getRoute(NavTarget.HomeScreen),
                navigator = appNavigator
            )
        }
    }
}