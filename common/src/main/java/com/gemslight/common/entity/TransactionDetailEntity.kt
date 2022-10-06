package com.gemslight.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "transaction_detail")
class TransactionDetailEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "document_code")
    val documentCode: String,
    @field:ColumnInfo(name = "document_number")
    val documentNumber: String,
    @field:ColumnInfo(name = "product_code")
    val productCode: String,
    @field:ColumnInfo(name = "price")
    val price: Double,
    @field:ColumnInfo(name = "quantity")
    val quantity: Int,
    @field:ColumnInfo(name = "unit")
    val unit: String,
    @field:ColumnInfo(name = "sub_total")
    val subTotal: Double,
    @field:ColumnInfo(name = "currency")
    val currency: String
)