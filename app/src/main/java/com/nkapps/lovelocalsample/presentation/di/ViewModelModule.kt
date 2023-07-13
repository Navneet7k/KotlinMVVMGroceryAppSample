package com.nkapps.lovelocalsample.presentation.di

import android.app.Application
import com.nkapps.lovelocalsample.domain.usecase.*
import com.nkapps.lovelocalsample.presentation.viewmodel.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Singleton
    @Provides
    fun providesProductDetailViewModel(cartUseCase: CartUseCase) : ProductDetailViewModel{
        return ProductDetailViewModel(cartUseCase)
    }

    @Singleton
    @Provides
    fun providesCartViewModel(cartUseCase: CartUseCase) : CartViewModel{
        return CartViewModel(cartUseCase)
    }

    @Singleton
    @Provides
    fun providesHomeViewModel(app : Application, productUseCase: ProductUseCase) : HomeViewModel{
        return HomeViewModel(app, productUseCase)
    }

}