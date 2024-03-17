package com.raka.football.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.data.CallResult
import com.raka.football.ui.detail.DetailScreen
import com.raka.football.ui.detail.DetailViewModel
import com.raka.football.ui.home.HomeScreen
import com.raka.football.ui.home.HomeViewModel
import com.raka.football.ui.navigation.MainNavigation.Detail.navigateTo

/**
 * MainNavigation graph that contains first app page
 */
sealed class MainNavigation(override val route: String) : Navigation(route) {
    override val graphId = "Root"

    companion object {
        /**
         * get all destinations on this graph
         */
        fun getAllNavigation() = setOf(Home, Detail)
    }

    object Home : MainNavigation("HOME") {
        object ArgKeys {
            const val SORTED = "SORTED"
        }

        class Arguments(savedStateHandle: SavedStateHandle) {
            val sorted = SavedArgument(savedStateHandle, ArgKeys.SORTED, false)
        }

        context(NavGraphBuilder) override fun compose(controller: NavController) {
            composable(route = getFullRoute(), arguments = getArguments()) {
                val viewModel: HomeViewModel = hiltViewModel()
                val allLaunchesList by viewModel.allTeamsList.data.collectAsStateWithLifecycle(
                    initialValue = CallResult.idle()
                )
                HomeScreen(
                    callResult = allLaunchesList,
                    onSortClicked = viewModel::sortByValue
                ) { controller.navigateTo(teamId = it) }
            }
        }
    }

    object Detail : MainNavigation("DETAIL") {
        object ArgKeys {
            const val TEAM_ID = "TEAM_ID"
        }

        class Arguments(savedStateHandle: SavedStateHandle) {
            val teamId = SavedArgument(savedStateHandle, ArgKeys.TEAM_ID, "")
        }

        fun NavController.navigateTo(teamId: String) {
            navigate(route = "$route/$teamId")
        }

        override fun getArguments() = listOf(
            navArgument(ArgKeys.TEAM_ID) {
                type = NavType.StringType
                defaultValue = ""
            }
        )

        context(NavGraphBuilder) override fun compose(controller: NavController) {
            composable(route = getFullRoute(), arguments = getArguments()) {
                val viewModel: DetailViewModel = hiltViewModel()
                val callResult by viewModel.footballTeam.collectAsStateWithLifecycle()
                DetailScreen(callResult = callResult) {
                    controller.popBackStack()
                }
            }
        }
    }
}