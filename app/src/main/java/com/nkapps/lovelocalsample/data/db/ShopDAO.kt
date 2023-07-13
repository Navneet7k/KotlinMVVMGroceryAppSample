package com.nkapps.lovelocalsample.data.db

import androidx.room.*
import com.nkapps.lovelocalsample.data.model.CartItem2
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cartItem2: CartItem2)

    @Update
    suspend fun updateCart(cartItem2: CartItem2)

    @Delete
    suspend fun deleteCart(cartItem2: CartItem2)

    @Query("select * from cart")
    fun cartItems() : Flow<List<CartItem2>>

    @Query("delete from cart")
    suspend fun clearAll()

}