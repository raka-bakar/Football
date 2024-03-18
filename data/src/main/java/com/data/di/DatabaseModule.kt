package com.data.di

import android.content.Context
import androidx.room.Room
import com.data.db.CacheDatabase
import com.data.db.FootballTeamDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DatabaseModule {
    /**
     * provides room database instance
     */
    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): CacheDatabase {
        return Room.databaseBuilder(
            context,
            CacheDatabase::class.java,
            "cache"
        )
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLaunchDao(cacheDatabase: CacheDatabase): FootballTeamDao {
        return cacheDatabase.footballDao()
    }
}