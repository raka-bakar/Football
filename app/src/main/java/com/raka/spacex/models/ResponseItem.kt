package com.raka.spacex.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseItem(

    @field:SerializedName("launchpad")
    val launchpad: String? = null,

    @field:SerializedName("payloads")
    val payloads: List<String?>? = null,

    @field:SerializedName("rocket")
    val rocket: String? = null,

    @field:SerializedName("crew")
    val crew: List<Any?>? = null,

    @field:SerializedName("date_unix")
    val dateUnix: Int? = null,

    @field:SerializedName("cores")
    val cores: List<CoresItem?>? = null,

    @field:SerializedName("auto_update")
    val autoUpdate: Boolean? = null,

    @field:SerializedName("date_precision")
    val datePrecision: String? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("details")
    val details: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("net")
    val net: Boolean? = null,

    @field:SerializedName("capsules")
    val capsules: List<Any?>? = null,

    @field:SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String? = null,

    @field:SerializedName("failures")
    val failures: List<FailuresItem?>? = null,

    @field:SerializedName("date_local")
    val dateLocal: String? = null,

    @field:SerializedName("flight_number")
    val flightNumber: Int? = null,

    @field:SerializedName("launch_library_id")
    val launchLibraryId: Any? = null,

    @field:SerializedName("fairings")
    val fairings: Fairings? = null,

    @field:SerializedName("ships")
    val ships: List<Any?>? = null,

    @field:SerializedName("date_utc")
    val dateUtc: String? = null,

    @field:SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("tbd")
    val tbd: Boolean? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("window")
    val window: Int? = null,

    @field:SerializedName("upcoming")
    val upcoming: Boolean? = null
):Serializable