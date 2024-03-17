package com.data.api

import com.data.CallResult
import com.data.models.response.teams.AllTeamsResponseItem
import okhttp3.internal.toHeaderList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    /**
     * get list of All teams
     * @return response of list of AllTeamsResponseItem
     */
    @GET("clubs.json")
    suspend fun getAllTeams(): Response<List<AllTeamsResponseItem>>

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