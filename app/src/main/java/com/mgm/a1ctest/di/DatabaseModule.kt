package com.mgm.a1ctest.di

import android.content.Context
import androidx.room.Room
import com.mgm.a1ctest.db.HistDatabase
import com.mgm.a1ctest.db.HistModel
import com.mgm.a1ctest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, HistDatabase::class.java, Constants.HISTORY_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: HistDatabase)= db.histDao()

    @Provides
    @Singleton
    fun provideHistModel() = HistModel()
}
