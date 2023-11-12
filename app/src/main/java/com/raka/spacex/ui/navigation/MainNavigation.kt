package com.raka.spacex.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.raka.spacex.ui.components.AllLaunchesListScreen
import com.raka.spacex.viewmodel.AllLaunchesViewModel

/**
 * MainNavigation graph that contains first app page
 */
sealed class MainNavigation(override val route: String) : Navigation(route) {
    override val graphId = "Root"

    companion object {
        /**
         * get all destinations on this graph
         */
        fun getAllNavigation() = setOf(LaunchesList)
    }

    object LaunchesList : MainNavigation("ALL_LAUNCHES") {

        context(NavGraphBuilder) override fun compose(controller: NavController) {
            composable(route = getFullRoute(), arguments = getArguments()) {
                val viewModel: AllLaunchesViewModel = hiltViewModel()
                val allLaunchesList by viewModel.allLaunchesList.collectAsStateWithLifecycle()
                AllLaunchesListScreen(callResult = allLaunchesList) {}
            }
        }
    }
}