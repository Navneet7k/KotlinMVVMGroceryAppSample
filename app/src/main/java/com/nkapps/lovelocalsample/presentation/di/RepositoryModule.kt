package com.nkapps.lovelocalsample.presentation.di

import com.nkapps.lovelocalsample.data.repository.ShopRepositoryImpl
import com.nkapps.lovelocalsample.data.repository.datasource.ShopLocalDataSource
import com.nkapps.lovelocalsample.data.repository.datasource.ShopRemoteDataSource
import com.nkapps.lovelocalsample.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesShopRepository(shopRemoteDataSource: ShopRemoteDataSource,localDataSource: ShopLocalDataSource) : ShopRepository{
        return ShopRepositoryImpl(shopRemoteDataSource,localDataSource)
    }

}