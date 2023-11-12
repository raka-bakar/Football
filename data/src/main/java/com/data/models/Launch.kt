package com.data.models

import java.time.ZonedDateTime

data class Launch(
    val id: String = "",
    val flightNumber: String = "",
    val imgUrl: String = "",
    val isSuccess: Boolean = false,
    val name: String = "",
    val date: ZonedDateTime? = null,
    val details: String
)