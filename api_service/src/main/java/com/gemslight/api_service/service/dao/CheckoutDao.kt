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

    @Query("SELECT * from transaction_header WHERE user=:user")
    fun getAllTransactionHeader(user: String): LiveData<List<Transaction>>

    @Transaction
    @Query("SELECT * from transaction_detail WHERE document_number=:documentNumber")
    fun getAllTransactionDetail(documentNumber: Int): LiveData<ProductCartCheckout>
}