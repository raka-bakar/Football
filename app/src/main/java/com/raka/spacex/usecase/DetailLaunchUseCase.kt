package com.raka.spacex.usecase

import com.raka.spacex.repository.LaunchRepository
import javax.inject.Inject

interface DetailLaunchUseCase {

//    fun getDetailLaunch(id: String): Flow<CallResult<DetailLaunch>>
}
class DetailLaunchUseCaseImpl @Inject constructor(private val repository: LaunchRepository) :
    DetailLaunchUseCase {
//    override fun getDetailLaunch(id: String): Flow<CallResult<DetailLaunch>> {
//        return repository.getSingleLaunch(id)
//    }
}