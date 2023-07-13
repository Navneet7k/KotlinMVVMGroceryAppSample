package com.nkapps.lovelocalsample.data.repository.datasourceImpl

import com.nkapps.lovelocalsample.data.db.ShopDAO
import com.nkapps.lovelocalsample.data.model.CartItem2
import com.nkapps.lovelocalsample.data.repository.datasource.ShopLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopLocalDataSourceImpl @Inject constructor(
    private val shopDAO: ShopDAO
)  : ShopLocalDataSource {
    override suspend fun addToCart(cartItem2: CartItem2) {
        return shopDAO.addToCart(cartItem2)
    }

    override fun getCartItems(): Flow<List<CartItem2>> {
        return shopDAO.cartItems()
    }

    override suspend fun updateCartItems(cartItem2: CartItem2) {
        return shopDAO.updateCart(cartItem2)
    }

    override suspend fun deleteCartItems(cartItem2: CartItem2) {
        return shopDAO.deleteCart(cartItem2)
    }

    override suspend fun clearCart() {
        return shopDAO.clearAll()
    }

}