package com.data

import com.data.api.ApiService
import com.data.models.TeamItem
import com.data.persist.DbTeamItem
import com.data.persist.FootballTeamDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * DataSource to fetch data from a local or network source
 */
internal interface DataSource {

    /**
     * get list of football teams
     * @return response of list of team
     */
    fun getAllTeams(sortedByValue: Boolean): Flow<CallResult<List<TeamItem>>>

    /**
     * get a saved TeamItem,
     * @param request that contains the ID of the football team
     * @return flow of one TeamItem
     */
    fun getSingleTeam(teamId: String): Flow<CallResult<TeamItem>>
}

internal class DataSourceImpl @Inject constructor(
    private val api: ApiService,
    private val dao: FootballTeamDao
) : DataSource {
    override fun getAllTeams(sortedByValue: Boolean): Flow<CallResult<List<TeamItem>>> {
        return getNetworkFlow({
            val res = ApiService.apiCall { api.getAllTeams() }
            res.copyConvert {
                it?.map { item ->
                    TeamItem(
                        id = item.id,
                        country = item.country ?: "",
                        name = item.name ?: "",
                        europeanTitles = item.europeanTitles ?: 0,
                        value = item.value ?: 0,
                        image = item.image ?: ""
                    )
                }
                    ?: emptyList()
            }
        }) { items, _ ->
            val dbList = items.map { item ->
                DbTeamItem(
                    id = item.id,
                    country = item.country,
                    name = item.name,
                    europeanTitles = item.europeanTitles,
                    value = item.value,
                    image = item.image
                )
            }
            dao.insertTeamItem(dbList)
        }.map { result ->
            result.copyConvert { list ->
                if (sortedByValue) {
                    list?.sortedByDescending {
                        it.value
                    }
                } else {
                    list?.sortedBy {
                        it.name
                    }
                }
            }
        }
    }

    override fun getSingleTeam(teamId: String):
        Flow<CallResult<TeamItem>> = flow {
        val dbItem = dao.loadSingleTeam(teamId)
        emit(
            CallResult.success(
                TeamItem(
                    id = dbItem.id,
                    country = dbItem.country,
                    name = dbItem.name,
                    europeanTitles = dbItem.europeanTitles,
                    value = dbItem.value,
                    image = dbItem.image
                )
            )
        )
    }

    /**
     * Helper method that executes and emits network calls as a single Flow
     * @param networkCall method containing network call invocation
     * @param saveCallResult optional method responsible for persisting network results to database
     * @return Flow that will report data state
     */
    private fun <T> getNetworkFlow(
        networkCall: suspend () -> CallResult<T>,
        saveCallResult: (suspend (T, extra: Map<String, String>) -> Unit)? = null
    ): Flow<CallResult<T>> =
        flow<CallResult<T>> {
            coroutineScope {
                val responseCall = async(Dispatchers.IO) { networkCall() }
                val response = responseCall.await()
                if (response.isSuccess() && response.data != null) {
                    emit(CallResult.success(response.data))
                    saveCallResult?.invoke(response.data, response.extra)
                } else if (response.isFail()) {
                    val localCall = async(Dispatchers.IO) { getLocalData() }
                    val localData = localCall.await()
                    if (localData.isEmpty()) {
                        emit(CallResult.error(response.message, response.code))
                    } else {
                        emit(CallResult.success(localData as T))
                    }
                }
            }
        }.onStart { emit(CallResult.loading()) }

    private fun getLocalData(): List<TeamItem> {
        val result = dao.loadAllTeams().map { item ->
            TeamItem(
                id = item.id,
                country = item.country,
                name = item.name,
                europeanTitles = item.europeanTitles,
                value = item.value,
                image = item.image
            )
        }
        return result
    }
}