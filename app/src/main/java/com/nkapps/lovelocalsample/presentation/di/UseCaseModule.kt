package com.nkapps.lovelocalsample.presentation.di

import com.nkapps.lovelocalsample.domain.repository.ShopRepository
import com.nkapps.lovelocalsample.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {


    @Singleton
    @Provides
    fun providesCartUseCase(repository: ShopRepository) : CartUseCase{
        return CartUseCase(repository)
    }

    @Singleton
    @Provides
    fun providesProductUseCase(repository: ShopRepository) : ProductUseCase{
        return ProductUseCase(repository)
    }

}