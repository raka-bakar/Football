package com.data.persist

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DbLaunchItem::class], version = 1)
@TypeConverters(Converter::class)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun feedDao(): LaunchDao
}