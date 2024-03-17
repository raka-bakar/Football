package com.raka.football

import androidx.lifecycle.SavedStateHandle
import com.data.CallResult
import com.data.models.TeamItem
import com.raka.football.ui.detail.DetailViewModel
import com.raka.football.ui.navigation.MainNavigation
import com.raka.football.usecase.SingleTeamUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert
import org.junit.Test

class DetailViewModelTest {
    private val savedStateHandle = mockk<SavedStateHandle>()
    private val useCase = mockk<SingleTeamUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `football team is fetched correctly`() {
        val teamItem = TeamItem(
            id = "959cd262-cc20-4e9d-93c1-241c265c7bbe",
            country = "Frankreich",
            name = "Paris Saint German",
            europeanTitles = 0,
            value = 947,
            image = "https://public.allaboutapps.at/hiring/images/psg.png"
        )
        savedStateHandle.mockReturn(
            MainNavigation.Detail.ArgKeys.TEAM_ID,
            "959cd262-cc20-4e9d-93c1-241c265c7bbe"
        )
        every { useCase.getSingleTeam("959cd262-cc20-4e9d-93c1-241c265c7bbe") }.returns(
            flow {
                emit(CallResult.success(teamItem))
            }
        )
        val detailViewModel = DetailViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherIo = UnconfinedTestDispatcher(),
            singleTeamUseCase = useCase
        )
        Assert.assertEquals(teamItem, detailViewModel.footballTeam.value.data)
    }
}