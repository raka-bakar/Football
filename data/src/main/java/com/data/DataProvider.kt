package com.data

import android.content.Context
import com.data.di.DaggerAppComponent
import com.data.models.request.DetailLaunchApiRequest
import javax.inject.Inject

/**
 * This class is the entry point to the module, it exposes the data calls to the app
 */
class DataProvider(context: Context) {
    @Inject internal lateinit var dataSource: DataSource

    init {
        val component = DaggerAppComponent.builder().context(context).build()
        component.inject(this)
    }

    fun getAllLaunches() = dataSource.getAllLaunches()

    fun getSingleLaunch(request: DetailLaunchApiRequest) = dataSource.getSingleLaunch(request)
}