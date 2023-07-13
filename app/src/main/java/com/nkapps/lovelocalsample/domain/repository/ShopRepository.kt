package com.nkapps.lovelocalsample.domain.repository

import com.nkapps.lovelocalsample.data.model.*
import com.nkapps.lovelocalsample.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface ShopRepository {

    suspend fun getAllProducts() : Resource<Shop>

    suspend fun addToCartItems(cartItem2: CartItem2)
    fun getCartItems() : Flow<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart()

}