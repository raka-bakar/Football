package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Response(

    @field:SerializedName("Response")
    val response: List<ResponseItem?>? = null
) : Serializable