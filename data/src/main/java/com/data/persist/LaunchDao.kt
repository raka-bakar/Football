package com.data.persist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunchItem(item: List<DbLaunchItem>)

    @Transaction
    @Query("SELECT * FROM LaunchItem")
    fun loadLaunchItem(): List<DbLaunchItem>
}