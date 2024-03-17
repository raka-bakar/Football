package com.data.models.response.teams

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllTeamsResponseItem(

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "stadium")
    val stadium: Stadium? = null,

    @Json(name = "location")
    val location: Location? = null,

    @Json(name = "id")
    val id: String,

    @Json(name = "value")
    val value: Int? = null,

    @Json(name = "european_titles")
    val europeanTitles: Int? = null
)

data class Location(

    @Json(name = "lng")
    val lng: Any? = null,

    @Json(name = "lat")
    val lat: Any? = null
)

data class Stadium(

    @Json(name = "size")
    val size: Int? = null,

    @Json(name = "name")
    val name: String? = null
)