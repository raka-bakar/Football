package com.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbTeamItem::class], version = 1)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun footballDao(): FootballTeamDao
}