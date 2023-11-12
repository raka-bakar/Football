package com.data.di

import com.data.DataSource
import com.data.DataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
internal abstract class DataSourceModule {
    /**
     * provides datasource
     */
    @Binds
    @Singleton
    abstract fun provideDataSource(impl: DataSourceImpl): DataSource
}