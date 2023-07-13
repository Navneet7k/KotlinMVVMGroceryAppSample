package com.nkapps.lovelocalsample.presentation.di

import com.nkapps.lovelocalsample.presentation.adapter.CartAdapter
import com.nkapps.lovelocalsample.presentation.adapter.HomeAdapter
import com.nkapps.lovelocalsample.presentation.adapter.SearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesHomeAdapter() : HomeAdapter{
        return HomeAdapter()
    }

    @Singleton
    @Provides
    fun providesCartAdapter() : CartAdapter{
        return CartAdapter()
    }

    @Singleton
    @Provides
    fun providesSearchAdapter() : SearchAdapter{
        return SearchAdapter()
    }



}