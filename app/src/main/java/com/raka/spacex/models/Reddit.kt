package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reddit(

    @field:SerializedName("campaign")
    val campaign: Any? = null,

    @field:SerializedName("launch")
    val launch: Any? = null,

    @field:SerializedName("media")
    val media: Any? = null,

    @field:SerializedName("recovery")
    val recovery: Any? = null
): Serializable