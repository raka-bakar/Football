package com.raka.football.di

import com.raka.football.usecase.AllTeamsUseCase
import com.raka.football.usecase.AllTeamsUseCaseImpl
import com.raka.football.usecase.SingleTeamUseCase
import com.raka.football.usecase.SingleTeamUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindSingleTeamUseCase(useCase: SingleTeamUseCaseImpl): SingleTeamUseCase

    @Binds
    fun bindAllTeamsUseCase(useCase: AllTeamsUseCaseImpl): AllTeamsUseCase
}