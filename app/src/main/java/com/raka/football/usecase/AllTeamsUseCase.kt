package com.raka.football.usecase

import com.data.CallResult
import com.data.models.TeamItem
import com.raka.football.repository.FootballTeamsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UseCase to provide Football Teams data
 */
interface AllTeamsUseCase {
    /**
     * get a list of football teams and return it as a Flow
     * @param sortedByValue that contains value of sorted status
     * @return a flow CallResult that contains a list of TeamItem
     */
    fun getAllTeams(sortedByValue: Boolean): Flow<CallResult<List<TeamItem>>>
}
class AllTeamsUseCaseImpl @Inject constructor(
    private val footballTeamsRepository: FootballTeamsRepository
) :
    AllTeamsUseCase {
    override fun getAllTeams(sortedByValue: Boolean): Flow<CallResult<List<TeamItem>>> =
        footballTeamsRepository.getAllTeams(sortedByValue)
}