package com.gemslight.common.entity.relational

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkout")
data class ProductCartEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "product_code")
    val productCode: String,
    @field:ColumnInfo(name = "quantity")
    var quantity: Int,
    @field:ColumnInfo(name = "user")
    var user: String
)