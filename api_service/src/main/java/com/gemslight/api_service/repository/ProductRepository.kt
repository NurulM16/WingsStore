package com.gemslight.api_service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gemslight.api_service.service.dao.ProductDao
import com.gemslight.common.entity.ProductEntity
import com.gemslight.common.ext.AppExecutors

class ProductRepository(
    private val productDao: ProductDao,
    private val appExecutors: AppExecutors
) {
    private val resultProduct = MediatorLiveData<List<ProductEntity>>()

    fun getAllProduct(): LiveData<List<ProductEntity>> {
        val localData = productDao.getAllProduct()
        val localData2 = productDao.getAllProduct()

        resultProduct.addSource(localData) {
            if (it.isEmpty()) {
                appExecutors.diskIO.execute {
                    productDao.insertProduct(
                        ProductEntity(
                            "SKUSKILNP",
                            "So Klin pewangi",
                            15000.0,
                            "IDR",0.1,
                            "13 cm x 10 cm",
                            "PCS"
                        )
                    )
                    productDao.insertProduct(
                        ProductEntity(
                            "GIVBIRU",
                            "GIV Biru",
                            11000.0,
                            "IDR", 0.0,
                            "8 cm x 5 cm",
                            "PCS"
                        )
                    )
                    productDao.insertProduct(
                        ProductEntity(
                            "SOKLINLQ",
                            "So Klin Liquid",
                            18000.0,
                            "IDR", 0.0,
                            "15cm x 10cm",
                            "PCS"
                        )
                    )
                    productDao.insertProduct(
                        ProductEntity(
                            "GIVKUNING",
                            "Giv Kuning",
                            10000.0,
                            "IDR",0.0,
                            "8cm x 5cm",
                            "PCS"
                        )
                    )
                }
            }
        }
        resultProduct.addSource(localData2) {
            resultProduct.value = it
        }
        return resultProduct
    }

}