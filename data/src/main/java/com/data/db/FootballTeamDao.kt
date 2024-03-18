package com.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface FootballTeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamItem(item: List<DbTeamItem>)

    @Transaction
    @Query("SELECT * FROM TeamItem")
    suspend fun loadAllTeams(): List<DbTeamItem>

    @Transaction
    @Query("SELECT * FROM TeamItem WHERE id = :idTeam")
    suspend fun loadSingleTeam(idTeam: String): DbTeamItem
}