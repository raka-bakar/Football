package com.raka.spacex.di

import com.raka.spacex.repository.LaunchRepository
import com.raka.spacex.repository.LaunchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindLaunchRepository(repository: LaunchRepositoryImpl):LaunchRepository
}