package com.raka.football.usecase

import com.data.CallResult
import com.data.models.TeamItem
import com.raka.football.repository.FootballTeamsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UseCase to provide a Football Team data
 */
interface SingleTeamUseCase {
    /**
     * get a Football team data
     ** @param teamId Id of the football team
     * @return a flow of TeamItem
     */
    fun getSingleTeam(teamId: String): Flow<CallResult<TeamItem>>
}
class SingleTeamUseCaseImpl @Inject constructor(private val repository: FootballTeamsRepository) :
    SingleTeamUseCase {
    override fun getSingleTeam(teamId: String): Flow<CallResult<TeamItem>> {
        return repository.getSingleTeam(teamId)
    }
}