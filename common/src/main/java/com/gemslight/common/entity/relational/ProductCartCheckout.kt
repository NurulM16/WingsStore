package com.gemslight.common.entity.relational

import androidx.room.Embedded
import androidx.room.Relation
import com.gemslight.common.entity.ProductEntity


data class ProductCartCheckout(

    @Embedded
    var productCartEntity: ProductCartEntity,

    @Relation(
        parentColumn = "product_code",
        entityColumn = "product_code"
    )
    val productEntity: ProductEntity

)
