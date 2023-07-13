package com.nkapps.lovelocalsample.data.repository.datasourceImpl

import com.nkapps.lovelocalsample.data.api.ShopApiService
import com.nkapps.lovelocalsample.data.model.*
import com.nkapps.lovelocalsample.data.repository.datasource.ShopRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ShopRemoteDataSourceImpl @Inject constructor(
    private val apiService: ShopApiService
) : ShopRemoteDataSource {
    override suspend fun getAllProducts(): Response<Shop> {
        return apiService.getAllProducts()
    }

    override suspend fun getProduct(itemId: Int): Response<ShopItem> {
        return apiService.getProduct(itemId)
    }
}