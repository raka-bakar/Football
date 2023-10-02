package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Flickr(

    @field:SerializedName("small")
    val small: List<Any?>? = null,

    @field:SerializedName("original")
    val original: List<Any?>? = null
) : Serializable