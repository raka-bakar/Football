package com.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TeamItem")
data class DbTeamItem(
    @PrimaryKey
    val id: String,
    val country: String,
    val name: String,
    val europeanTitles: Int,
    val value: Int,
    val image: String
)