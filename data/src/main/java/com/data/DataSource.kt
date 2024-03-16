package com.data

import com.data.api.ApiService
import com.data.models.Launch
import com.data.models.request.DetailLaunchApiRequest
import com.data.models.response.singlelaunch.DetailLaunchApiResponse
import com.data.persist.DbLaunchItem
import com.data.persist.LaunchDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * DataSource to fetch data from a local or network source
 */
internal interface DataSource {

    /**
     * get list of feeds that match one of more of the tags
     * @return response of list of launches
     */
    fun getAllLaunches(): Flow<CallResult<List<Launch>>>

    /**
     * get the saved feedItem,
     * @param request that contains the ID of the launch
     * @return flow of one feedItem
     */
    fun getSingleLaunch(request: DetailLaunchApiRequest): Flow<CallResult<DetailLaunchApiResponse>>
}

internal class DataSourceImpl @Inject constructor(
    private val api: ApiService,
    private val dao: LaunchDao
) : DataSource {
    override fun getAllLaunches(): Flow<CallResult<List<Launch>>> {
        return getNetworkFlow({
            val res = ApiService.apiCall { api.getAllLaunches() }
            res.copyConvert {
                it?.map { item ->
                    Launch(
                        id = item.id ?: "0",
                        flightNumber = item.flightNumber.toString(),
                        imgUrl = item.links?.patch?.small ?: "",
                        date = item.dateLocal,
                        isSuccess = item.success ?: false,
                        name = item.name ?: "",
                        details = item.details ?: ""
                    )
                }
                    ?: emptyList()
            }
        }) { items, _ ->
            val dbList = items.map { item ->
                DbLaunchItem(
                    id = item.id,
                    flightNumber = item.flightNumber,
                    imgUrl = item.imgUrl,
                    date = item.date,
                    isSuccess = item.isSuccess,
                    name = item.name,
                    details = item.details
                )
            }
            dao.insertLaunchItem(dbList)
        }
    }

    override fun getSingleLaunch(request: DetailLaunchApiRequest):
        Flow<CallResult<DetailLaunchApiResponse>> {
        return getNetworkFlow({
            val res = ApiService.apiCall { api.getSingleLaunch(request.id) }
            res.copyConvert { it }
        })
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
                    val localCall = async(Dispatchers.IO){getLocalData()}
                    val localData = localCall.await()
                    if (localData.isEmpty()) {
                        emit(CallResult.error(response.message, response.code))
                    } else {
                        emit(CallResult.success(localData as T))
                    }
                }
            }
        }.onStart { emit(CallResult.loading()) }

    private fun getLocalData(): List<Launch> {
        val result = dao.loadLaunchItem().map {
            Launch(
                id = it.id,
                flightNumber = it.flightNumber,
                imgUrl = it.imgUrl,
                isSuccess = it.isSuccess,
                name = it.name,
                date = it.date,
                details = it.details
            )
        }
        return result
    }
}