package com.raka.football.repository

import com.data.CallResult
import com.data.DataProvider
import com.data.models.TeamItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository to provide Football Teams data
 */
interface FootballTeamsRepository {
    /**
     * get a list of football teams and return it as a Flow
     * @param sortedByValue that contains value of sorted status
     * @return a flow of list of TeamItem
     */
    fun getAllTeams(sortedByValue: Boolean): Flow<CallResult<List<TeamItem>>>

    /**
     * get a Football team data
     ** @param teamId Id of the football team
     * @return a flow of TeamItem
     */
    fun getSingleTeam(teamId: String): Flow<CallResult<TeamItem>>
}

class FootballTeamsRepositoryImpl @Inject constructor(private val dataProvider: DataProvider) :
    FootballTeamsRepository {
    override fun getAllTeams(sortedByValue: Boolean): Flow<CallResult<List<TeamItem>>> {
        return dataProvider.getAllTeams(sortedByValue)
    }

    override fun getSingleTeam(teamId: String): Flow<CallResult<TeamItem>> {
        return dataProvider.getSingleTeam(teamId)
    }
}