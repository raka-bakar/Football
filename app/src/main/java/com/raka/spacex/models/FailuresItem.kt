package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FailuresItem(

    @field:SerializedName("altitude")
    val altitude: Any? = null,

    @field:SerializedName("reason")
    val reason: String? = null,

    @field:SerializedName("time")
    val time: Int? = null
) : Serializable