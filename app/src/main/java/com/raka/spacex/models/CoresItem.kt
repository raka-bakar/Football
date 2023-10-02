package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CoresItem(

    @field:SerializedName("core")
    val core: String? = null,

    @field:SerializedName("flight")
    val flight: Int? = null,

    @field:SerializedName("landing_type")
    val landingType: Any? = null,

    @field:SerializedName("gridfins")
    val gridfins: Boolean? = null,

    @field:SerializedName("landing_attempt")
    val landingAttempt: Boolean? = null,

    @field:SerializedName("legs")
    val legs: Boolean? = null,

    @field:SerializedName("landpad")
    val landpad: Any? = null,

    @field:SerializedName("reused")
    val reused: Boolean? = null,

    @field:SerializedName("landing_success")
    val landingSuccess: Any? = null
) : Serializable