package com.raka.spacex.repository

import com.data.CallResult
import com.data.DataProvider
import com.data.models.Launch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LaunchRepository {
    fun getAllLaunches(): Flow<CallResult<List<Launch>>>

//    fun getSingleLaunch(id:String): Flow<CallResult<DetailLaunch>>
}

class LaunchRepositoryImpl @Inject constructor(private val dataProvider: DataProvider) :
    LaunchRepository {
    override fun getAllLaunches(): Flow<CallResult<List<Launch>>> {
        return dataProvider.getAllLaunches()
    }

//    override fun getSingleLaunch(id: String): Flow<CallResult<DetailLaunch>> {
//        val request = DetailLaunchApiRequest(id = id)
//        dataProvider.getSingleLaunch(request)
//    }
}