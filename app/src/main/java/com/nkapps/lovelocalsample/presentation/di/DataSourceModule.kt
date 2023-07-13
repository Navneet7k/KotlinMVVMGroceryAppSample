package com.nkapps.lovelocalsample.presentation.di

import com.nkapps.lovelocalsample.data.api.ShopApiService
import com.nkapps.lovelocalsample.data.db.ShopDAO
import com.nkapps.lovelocalsample.data.repository.datasource.ShopLocalDataSource
import com.nkapps.lovelocalsample.data.repository.datasource.ShopRemoteDataSource
import com.nkapps.lovelocalsample.data.repository.datasourceImpl.ShopLocalDataSourceImpl
import com.nkapps.lovelocalsample.data.repository.datasourceImpl.ShopRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(shopDAO: ShopDAO) : ShopLocalDataSource {
        return ShopLocalDataSourceImpl(shopDAO)
    }

    @Singleton
    @Provides
    fun provideShopRemoteDataSource(shopApiService: ShopApiService) : ShopRemoteDataSource {
        return ShopRemoteDataSourceImpl(shopApiService)
    }

}