package com.data.persist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "LaunchItem")
data class DbLaunchItem(
    @PrimaryKey
    val id: String,
    val flightNumber: String,
    val imgUrl: String,
    val isSuccess: Boolean,
    val name: String,
    val date: ZonedDateTime? = null,
    val details: String
)