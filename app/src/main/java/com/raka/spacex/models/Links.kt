package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Links(

    @field:SerializedName("patch")
    val patch: Patch? = null,

    @field:SerializedName("webcast")
    val webcast: String? = null,

    @field:SerializedName("flickr")
    val flickr: Flickr? = null,

    @field:SerializedName("reddit")
    val reddit: Reddit? = null,

    @field:SerializedName("wikipedia")
    val wikipedia: String? = null,

    @field:SerializedName("youtube_id")
    val youtubeId: String? = null,

    @field:SerializedName("presskit")
    val presskit: Any? = null,

    @field:SerializedName("article")
    val article: String? = null
): Serializable