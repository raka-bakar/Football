package com.data.models.response.singlelaunch

import com.squareup.moshi.Json

data class DetailLaunchApiResponse(

    @Json(name = "launchpad")
    val launchpad: String? = null,

    @Json(name = "payloads")
    val payloads: List<String?>? = null,

    @Json(name = "rocket")
    val rocket: String? = null,

    @Json(name = "crew")
    val crew: List<Any?>? = null,

    @Json(name = "date_unix")
    val dateUnix: Int? = null,

    @Json(name = "cores")
    val cores: List<CoresItem?>? = null,

    @Json(name = "auto_update")
    val autoUpdate: Boolean? = null,

    @Json(name = "date_precision")
    val datePrecision: String? = null,

    @Json(name = "links")
    val links: Links? = null,

    @Json(name = "details")
    val details: String? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "net")
    val net: Boolean? = null,

    @Json(name = "capsules")
    val capsules: List<Any?>? = null,

    @Json(name = "static_fire_date_utc")
    val staticFireDateUtc: String? = null,

    @Json(name = "failures")
    val failures: List<FailuresItem?>? = null,

    @Json(name = "date_local")
    val dateLocal: String? = null,

    @Json(name = "flight_number")
    val flightNumber: Int? = null,

    @Json(name = "launch_library_id")
    val launchLibraryId: Any? = null,

    @Json(name = "fairings")
    val fairings: Fairings? = null,

    @Json(name = "ships")
    val ships: List<Any?>? = null,

    @Json(name = "date_utc")
    val dateUtc: String? = null,

    @Json(name = "static_fire_date_unix")
    val staticFireDateUnix: Int? = null,

    @Json(name = "success")
    val success: Boolean? = null,

    @Json(name = "tbd")
    val tbd: Boolean? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "window")
    val window: Int? = null,

    @Json(name = "upcoming")
    val upcoming: Boolean? = null
)

data class CoresItem(

    @Json(name = "core")
    val core: String? = null,

    @Json(name = "flight")
    val flight: Int? = null,

    @Json(name = "landing_type")
    val landingType: Any? = null,

    @Json(name = "gridfins")
    val gridfins: Boolean? = null,

    @Json(name = "landing_attempt")
    val landingAttempt: Boolean? = null,

    @Json(name = "legs")
    val legs: Boolean? = null,

    @Json(name = "landpad")
    val landpad: Any? = null,

    @Json(name = "reused")
    val reused: Boolean? = null,

    @Json(name = "landing_success")
    val landingSuccess: Any? = null
)

data class Flickr(

    @Json(name = "small")
    val small: List<Any?>? = null,

    @Json(name = "original")
    val original: List<Any?>? = null
)

data class Reddit(

    @Json(name = "campaign")
    val campaign: Any? = null,

    @Json(name = "launch")
    val launch: Any? = null,

    @Json(name = "media")
    val media: Any? = null,

    @Json(name = "recovery")
    val recovery: Any? = null
)

data class Fairings(

    @Json(name = "recovered")
    val recovered: Boolean? = null,

    @Json(name = "ships")
    val ships: List<Any?>? = null,

    @Json(name = "recovery_attempt")
    val recoveryAttempt: Boolean? = null,

    @Json(name = "reused")
    val reused: Boolean? = null
)

data class Patch(

    @Json(name = "small")
    val small: String? = null,

    @Json(name = "large")
    val large: String? = null
)

data class FailuresItem(

    @Json(name = "altitude")
    val altitude: Any? = null,

    @Json(name = "reason")
    val reason: String? = null,

    @Json(name = "time")
    val time: Int? = null
)

data class Links(

    @Json(name = "patch")
    val patch: Patch? = null,

    @Json(name = "webcast")
    val webcast: String? = null,

    @Json(name = "flickr")
    val flickr: Flickr? = null,

    @Json(name = "reddit")
    val reddit: Reddit? = null,

    @Json(name = "wikipedia")
    val wikipedia: String? = null,

    @Json(name = "youtube_id")
    val youtubeId: String? = null,

    @Json(name = "presskit")
    val presskit: Any? = null,

    @Json(name = "article")
    val article: String? = null
)