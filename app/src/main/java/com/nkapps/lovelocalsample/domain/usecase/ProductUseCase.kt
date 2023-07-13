package com.nkapps.lovelocalsample.domain.usecase

import com.nkapps.lovelocalsample.data.model.Shop
import com.nkapps.lovelocalsample.data.util.Resource
import com.nkapps.lovelocalsample.domain.repository.ShopRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getAllProducts() : Resource<Shop> {
        return repository.getAllProducts()
    }

}