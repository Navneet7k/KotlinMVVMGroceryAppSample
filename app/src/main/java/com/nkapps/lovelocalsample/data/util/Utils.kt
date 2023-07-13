package com.nkapps.lovelocalsample.data.util


object Utils {

    fun formatPrice(price : String): String {
        return String.format("%.2f", price.toDouble())
    }

}