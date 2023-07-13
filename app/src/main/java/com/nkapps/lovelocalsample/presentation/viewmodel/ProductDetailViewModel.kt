package com.nkapps.lovelocalsample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkapps.lovelocalsample.data.model.CartItem2
import com.nkapps.lovelocalsample.data.model.ShopItem
import com.nkapps.lovelocalsample.domain.usecase.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel  @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    fun saveToCart(cartItem2: CartItem2) = viewModelScope.launch(IO) {
        cartUseCase.addToCartItem(cartItem2)
    }

}