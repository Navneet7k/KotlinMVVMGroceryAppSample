package com.nkapps.lovelocalsample.presentation.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.nkapps.lovelocalsample.data.db.Converters
import com.nkapps.lovelocalsample.data.db.ShopDAO
import com.nkapps.lovelocalsample.data.db.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesShopDatabase(app : Application,gson : Gson) : ShopDatabase{
        return Room.databaseBuilder(app,ShopDatabase::class.java,"shop_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesShopDao(database: ShopDatabase) : ShopDAO{
        return database.shopDao()
    }

}