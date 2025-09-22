package com.example.onlineshop.module

import android.content.Context
import androidx.room.Room
import com.example.onlineshop.config.AppDatabase
import com.example.onlineshop.dao.BasketEntityDao
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "online_shop_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBasketEntityDao(db: AppDatabase): BasketEntityDao = db.basketEntityDao()
}