package com.raka.spacex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.CallResult
import com.raka.spacex.usecase.AllLaunchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class AllLaunchesViewModel @Inject constructor(
    private val allLaunchUseCase: AllLaunchUseCase,
    dispatcherIo: CoroutineDispatcher,
) : ViewModel() {

    val allLaunchesList = allLaunchUseCase.getAllLaunches().stateIn(
        viewModelScope + dispatcherIo,
        SharingStarted.Eagerly,
        CallResult.loading()
    )
}