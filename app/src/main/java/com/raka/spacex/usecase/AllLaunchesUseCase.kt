package com.raka.spacex.usecase

import com.data.CallResult
import com.data.models.Launch
import com.raka.spacex.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AllLaunchUseCase {
    fun getAllLaunches(): Flow<CallResult<List<Launch>>>
}
class AllLaunchUseCaseImpl @Inject constructor(private val launchRepository: LaunchRepository) :
    AllLaunchUseCase {
    override fun getAllLaunches(): Flow<CallResult<List<Launch>>> =
        launchRepository.getAllLaunches()
}