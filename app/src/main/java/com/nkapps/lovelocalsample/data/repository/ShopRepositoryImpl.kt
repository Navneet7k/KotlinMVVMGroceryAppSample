package com.nkapps.lovelocalsample.data.repository

import com.nkapps.lovelocalsample.data.model.*
import com.nkapps.lovelocalsample.data.repository.datasource.ShopLocalDataSource
import com.nkapps.lovelocalsample.data.repository.datasource.ShopRemoteDataSource
import com.nkapps.lovelocalsample.data.util.Resource
import com.nkapps.lovelocalsample.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val remoteDataSource: ShopRemoteDataSource,
    private val localDataSource: ShopLocalDataSource
) : ShopRepository {


    override suspend fun getAllProducts(): Resource<Shop> {
        return responseToShopResult(remoteDataSource.getAllProducts())
    }

    override suspend fun addToCartItems(cartItem2: CartItem2) {
        return localDataSource.addToCart(cartItem2)
    }

    override fun getCartItems(): Flow<List<CartItem2>> {
        return localDataSource.getCartItems()
    }

    override suspend fun updateCartItems(cartItem2: CartItem2) {
        return localDataSource.updateCartItems(cartItem2)
    }

    override suspend fun deleteCartItems(cartItem2: CartItem2) {
        return localDataSource.deleteCartItems(cartItem2)
    }

    override suspend fun clearCart() {
        return localDataSource.clearCart()
    }

    private fun responseToShopResult(response: Response<Shop>) : Resource<Shop>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

}