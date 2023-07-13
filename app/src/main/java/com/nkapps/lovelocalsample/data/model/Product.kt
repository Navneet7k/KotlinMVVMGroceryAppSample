package com.nkapps.lovelocalsample.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int,
)