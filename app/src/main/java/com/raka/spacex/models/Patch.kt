package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Patch(

    @field:SerializedName("small")
    val small: String? = null,

    @field:SerializedName("large")
    val large: String? = null
): Serializable