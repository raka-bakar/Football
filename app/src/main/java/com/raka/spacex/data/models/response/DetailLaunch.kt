package com.raka.spacex.data.models.response

data class DetailLaunch(
    val id: String,
    val flightNumber: String,
    val imgUrl: String,
    val isSuccess: Boolean,
    val name: String,
    val date: String,
    val description: String
)