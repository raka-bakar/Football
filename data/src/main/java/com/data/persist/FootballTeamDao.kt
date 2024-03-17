package com.data.persist

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
    fun loadAllTeams(): List<DbTeamItem>

    @Transaction
    @Query("SELECT * FROM TeamItem WHERE id = :idTeam")
    fun loadSingleTeam(idTeam: String): DbTeamItem
}