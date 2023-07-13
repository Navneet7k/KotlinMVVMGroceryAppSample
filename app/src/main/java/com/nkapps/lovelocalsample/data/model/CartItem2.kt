package com.nkapps.lovelocalsample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem2(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val price: String,
    val title: String,
    val quantity: Int,
    val pricePerItem: Double,
)