package com.gemslight.api_service.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.gemslight.api_service.service.dao.CheckoutDao
import com.gemslight.api_service.service.storage.UserStorageService
import com.gemslight.common.entity.relational.ProductCartCheckout
import com.gemslight.common.entity.relational.ProductCartEntity
import com.gemslight.common.entity.TransactionDetailEntity
import com.gemslight.common.entity.TransactionHeaderEntity
import com.gemslight.common.ext.AppExecutors
import java.time.LocalDateTime


class CheckoutAndTransactionRepository(
    private val checkoutDao: CheckoutDao,
    private val appExecutors: AppExecutors,
    private val userStorageService: UserStorageService
) {


    fun getProductCart(): LiveData<List<ProductCartCheckout>> {
        return checkoutDao.getCheckout(userStorageService.getUser())
    }

    fun insertProductToCart(productCartEntity: ProductCartEntity) {
        appExecutors.diskIO.execute {
            checkoutDao.insertProductCart(productCartEntity)
        }
    }

    fun getDiscountPrice(price: Double, discount: Double) = price - (price * discount / 100)


    fun getSubTotal(productCartCheckout: ProductCartCheckout) =
        getDiscountPrice(
            productCartCheckout.productEntity.price,
            productCartCheckout.productEntity.discount
        ) * productCartCheckout.productCartEntity.quantity

    fun getTotal(subTotalList: List<ProductCartCheckout>): Double {
        var total = 0.0
        subTotalList.forEach {
            total += getSubTotal(it)
        }
        return total
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun insertProductCheckoutToTransactionDetail(
        checkout: List<ProductCartCheckout>,
        total: Double
    ) {
        val date = LocalDateTime.now().toString()
        val transactionDetail = arrayListOf<TransactionDetailEntity>()
        val documentCode = "TRX"
        val documentNumber = 0
        val transactionHeader = TransactionHeaderEntity(
            documentCode, documentNumber,
            userStorageService.getUser(), total, date
        )


        checkout.forEach {
            transactionDetail.add(
                TransactionDetailEntity(
                    documentCode,
                    documentNumber,
                    it.productEntity.productCode,
                    it.productEntity.price,
                    it.productCartEntity.quantity,
                    it.productEntity.unit,
                    getSubTotal(it),
                    it.productEntity.currency
                )
            )
        }
        appExecutors.diskIO.execute {
            checkoutDao.insertProductCheckoutToTransactionDetail(transactionDetail)
            checkoutDao.insertProductCheckoutToTransactionHeader(transactionHeader)
        }
    }
    fun deleteAllCart() {
        appExecutors.diskIO.execute{
            checkoutDao.deleteAllCart()
        }
    }

    fun getAllTransactionHeader() = checkoutDao.getAllTransactionHeader(userStorageService.getUser())

    fun getAllTransactionDetail(documentNumber: Int) = checkoutDao.getAllTransactionDetail(documentNumber)
}