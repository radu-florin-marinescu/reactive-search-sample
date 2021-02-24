package com.verdite.samples.reactivesearch.core.di

import android.content.Context
import androidx.room.Room
import com.verdite.samples.reactivesearch.core.Database
import com.verdite.samples.reactivesearch.data.dao.StockDao
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
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(
            context.applicationContext, Database::class.java, "StocksDB"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideStocksDao(db: Database): StockDao = db.stockDao()
}