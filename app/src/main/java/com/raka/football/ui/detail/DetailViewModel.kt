package com.raka.football.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.CallResult
import com.raka.football.ui.navigation.MainNavigation
import com.raka.football.usecase.SingleTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    singleTeamUseCase: SingleTeamUseCase,
    dispatcherIo: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = MainNavigation.Detail.Arguments(savedStateHandle)

    val teamId = args.teamId.get()

    val footballTeam = singleTeamUseCase.getSingleTeam(teamId)
        .stateIn(viewModelScope + dispatcherIo, SharingStarted.Eagerly, CallResult.loading())
}