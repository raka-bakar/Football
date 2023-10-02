package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Fairings(

    @field:SerializedName("recovered")
    val recovered: Boolean? = null,

    @field:SerializedName("ships")
    val ships: List<Any?>? = null,

    @field:SerializedName("recovery_attempt")
    val recoveryAttempt: Boolean? = null,

    @field:SerializedName("reused")
    val reused: Boolean? = null
): Serializable