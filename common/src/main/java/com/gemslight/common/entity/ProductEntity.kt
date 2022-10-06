package com.gemslight.common.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "product")
class ProductEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "product_code")
    val productCode: String,
    @field:ColumnInfo(name = "product_name")
    val productName: String,
    @field:ColumnInfo(name = "price")
    val price: Double,
    @field:ColumnInfo(name = "currency")
    val currency: String,
    @field:ColumnInfo(name = "discount")
    val discount: Double,
    @field:ColumnInfo(name = "dimension")
    val dimension: String,
    @field:ColumnInfo(name = "unit")
    val unit: String
) : Parcelable