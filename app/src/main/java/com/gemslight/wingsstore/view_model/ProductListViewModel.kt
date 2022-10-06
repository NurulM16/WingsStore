package com.gemslight.wingsstore.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gemslight.api_service.repository.ProductRepository
import com.gemslight.common.base.AppResponse
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    application: Application,
    val productRepository: ProductRepository
) :
    BaseViewModel(application) {
    var productListDataState: LiveData<List<ProductEntity>>? = null

    init {
        getProductList()
    }
    fun getProductList() {
        viewModelScope.launch {
            productListDataState = productRepository.getAllProduct()
        }
    }
}