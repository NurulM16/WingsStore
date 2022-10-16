package com.gemslight.api_service.service.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gemslight.common.entity.TransactionDetailEntity
import com.gemslight.common.entity.TransactionHeaderEntity
import com.gemslight.common.entity.relational.ProductCartCheckout
import com.gemslight.common.entity.relational.ProductCartEntity


@Dao
interface CheckoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductCart(productCartEntity: ProductCartEntity)

    @Transaction
    @Query("SELECT * from checkout WHERE user=:user")
    fun getCheckout(user: String): LiveData<List<ProductCartCheckout>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductCheckoutToTransactionDetail(transactionDetailEntity: List<TransactionDetailEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductCheckoutToTransactionHeader(transactionHeaderEntity: TransactionHeaderEntity)

    @Query("DELETE from checkout")
    fun deleteAllCart()

}