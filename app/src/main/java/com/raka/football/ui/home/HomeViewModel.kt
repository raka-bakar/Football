package com.raka.football.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.CallResult
import com.raka.football.ui.navigation.MainNavigation
import com.raka.football.usecase.AllTeamsUseCase
import com.raka.football.util.RefreshFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    allTeamsUseCase: AllTeamsUseCase,
    dispatcherIo: CoroutineDispatcher,
) : ViewModel() {

    private val args = MainNavigation.Home.Arguments(savedStateHandle)

    private val sortedByValue = args.sorted.flow

    val allTeamsList = RefreshFlow {
        allTeamsUseCase.getAllTeams(sortedByValue = sortedByValue.value).stateIn(
            viewModelScope + dispatcherIo,
            SharingStarted.WhileSubscribed(),
            CallResult.loading()
        )
    }

    fun sortByValue() {
        args.sorted.set(!sortedByValue.value)
        allTeamsList.refresh()
    }
}