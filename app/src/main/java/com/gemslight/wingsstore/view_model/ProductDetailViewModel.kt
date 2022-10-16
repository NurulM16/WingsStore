package com.gemslight.wingsstore.view_model

import android.app.Application
import com.gemslight.api_service.repository.CheckoutAndTransactionRepository
import com.gemslight.api_service.service.storage.UserStorageService
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.relational.ProductCartEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    application: Application,
    val checkoutAndTransactionRepository: CheckoutAndTransactionRepository,
    private val userStorageService: UserStorageService
) :
    BaseViewModel(application) {

    fun insertProductToCart(productCode: String) {
        checkoutAndTransactionRepository.insertProductToCart(
            ProductCartEntity(
                productCode,
                1,
                userStorageService.getUser()
            )
        )


    }
}