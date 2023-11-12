package com.raka.spacex.di

import com.raka.spacex.usecase.AllLaunchUseCase
import com.raka.spacex.usecase.AllLaunchUseCaseImpl
import com.raka.spacex.usecase.DetailLaunchUseCase
import com.raka.spacex.usecase.DetailLaunchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindDetailLaunchUseCase(useCase: DetailLaunchUseCaseImpl): DetailLaunchUseCase

    @Binds
    fun bindAllLaunchesUseCase(useCase: AllLaunchUseCaseImpl): AllLaunchUseCase
}