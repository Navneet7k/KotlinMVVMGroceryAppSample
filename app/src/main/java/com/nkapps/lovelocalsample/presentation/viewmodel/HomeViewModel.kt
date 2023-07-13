package com.nkapps.lovelocalsample.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nkapps.lovelocalsample.data.model.Shop
import com.nkapps.lovelocalsample.data.util.Network
import com.nkapps.lovelocalsample.data.util.Network.isNetworkAvailable
import com.nkapps.lovelocalsample.data.util.Resource
import com.nkapps.lovelocalsample.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app : Application,
    private val productUseCase: ProductUseCase
) : AndroidViewModel(app){

    val products : MutableLiveData<Resource<Shop>> = MutableLiveData()

    fun getAllProducts() = viewModelScope.launch(IO) {
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = productUseCase.getAllProducts()
                products.postValue(apiResult)
            }else{
                products.postValue(Resource.Error(message = "Internet Failure"))
            }
        }catch (e : Exception){
            products.postValue(Resource.Error(message = e.localizedMessage ?: "Failed!"))
        }
    }

}