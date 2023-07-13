package com.nkapps.lovelocalsample.data.repository.datasource

import com.nkapps.lovelocalsample.data.model.*
import retrofit2.Response

interface ShopRemoteDataSource {
    suspend fun getAllProducts() : Response<Shop>
    suspend fun getProduct(itemId : Int) : Response<ShopItem>
}