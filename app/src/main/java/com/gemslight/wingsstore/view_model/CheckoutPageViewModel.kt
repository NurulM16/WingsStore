package com.gemslight.wingsstore.view_model

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gemslight.api_service.repository.CheckoutAndTransactionRepository
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.ProductCartCheckout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CheckoutPageViewModel @Inject constructor(
    application: Application,
    val checkoutAndTransactionRepository: CheckoutAndTransactionRepository
) : BaseViewModel(application) {
    var checkoutDataState: LiveData<List<ProductCartCheckout>>? = null
    val subTotalPriceProduct: MutableMap<ProductCartCheckout, Double> = mutableMapOf()


    init {
        getProductCheckout()
    }

    fun getProductCheckout() {
        viewModelScope.launch {
            checkoutDataState = checkoutAndTransactionRepository.getProductCart()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertProductCheckoutToTransactionDetail() {
        checkoutAndTransactionRepository.insertProductCheckoutToTransactionDetail(
            subTotalPriceProduct.keys.toList(),
            checkoutAndTransactionRepository.getTotal(subTotalPriceProduct.keys.toList())
        )
    }

    fun deleteAllCart() {
        viewModelScope.launch {
            checkoutAndTransactionRepository.deleteAllCart()
        }
    }
}