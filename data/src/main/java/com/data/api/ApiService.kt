package com.data.api

import com.data.CallResult
import com.data.models.response.alllaunches.AllLaunchesResponseItem
import com.data.models.response.singlelaunch.DetailLaunchApiResponse
import okhttp3.internal.toHeaderList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /**
     * get list of feeds that match one of more of the tags
     * @return response of list of launches
     */
    @GET("v4/launches")
    suspend fun getAllLaunches(): Response<List<AllLaunchesResponseItem>>

    /**
     * get list of feeds that match one of more of the tags
     * @param id of the launch
     * @return response of list of launches
     */
    @GET("v4/launches/{id}")
    suspend fun getSingleLaunch(@Path("id")id: String): Response<DetailLaunchApiResponse>

    companion object {
        /**
         * Helper method which can be used to safely do an API call from the interface
         * @param call the actual suspending network call
         * @return CallResult instance with transformed header and data
         */
        suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): CallResult<T> {
            try {
                val response = call.invoke()
                val body = response.body()
                if (response.isSuccessful) {
                    val headers = response.headers().toHeaderList().associate {
                        it.name.utf8() to it.value.utf8()
                    }
                    return CallResult.success(body, headers, "", response.code())
                }
                return CallResult.error(response.message(), response.code(), null)
            } catch (exception: Exception) {
                return CallResult.error(exception.message ?: exception.toString())
            }
        }
    }
}