package com.gemslight.api_service.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gemslight.api_service.service.dao.CheckoutDao
import com.gemslight.api_service.service.dao.LoginDao
import com.gemslight.api_service.service.dao.ProductDao
import com.gemslight.common.entity.*


@Database(
    entities = [LoginEntity::class,
        ProductEntity::class,
        ProductCartEntity::class,
        TransactionDetailEntity::class,
        TransactionHeaderEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class WingsStoreDB : RoomDatabase() {
    abstract fun loginDao(): LoginDao
    abstract fun productDao(): ProductDao
    abstract fun checkoutDao(): CheckoutDao


    companion object {
        private val instance: WingsStoreDB? = null
        fun getInstance(context: Context): WingsStoreDB =
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                WingsStoreDB::class.java, "penjualan.db"
            ).allowMainThreadQueries().build()
    }
}