package com.nkapps.lovelocalsample.data.api

import com.nkapps.lovelocalsample.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ShopApiService {

    @GET("/products")
    suspend fun getAllProducts() : Response<Shop>
}