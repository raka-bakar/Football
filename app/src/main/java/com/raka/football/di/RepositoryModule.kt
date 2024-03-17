package com.raka.football.di

import com.raka.football.repository.FootballTeamsRepository
import com.raka.football.repository.FootballTeamsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindFootballTeamsRepository(repository: FootballTeamsRepositoryImpl):FootballTeamsRepository
}