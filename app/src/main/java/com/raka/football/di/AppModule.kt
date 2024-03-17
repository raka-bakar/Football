package com.raka.football.di

import android.content.Context
import com.data.DataProvider
import com.raka.football.FootballApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext context: Context): FootballApp =
        context as FootballApp

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideDataProvider(@ApplicationContext context: Context): DataProvider {
        return DataProvider(context = context)
    }
}