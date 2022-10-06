package com.gemslight.api_service.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gemslight.api_service.service.dao.LoginDao
import com.gemslight.api_service.service.dao.ProductDao
import com.gemslight.common.entity.LoginEntity
import com.gemslight.common.entity.ProductEntity


@Database(
    entities = [LoginEntity::class,
        ProductEntity::class],
    version = 1, exportSchema = false
)
abstract class WingsStoreDB : RoomDatabase() {
    abstract fun loginDao(): LoginDao
    abstract fun productDao(): ProductDao
//    abstract fun transactionHeader(): TransactionHeaderDao
//    abstract fun transactionDetail(): TransactionDetailDao

    companion object {
        private val instance: WingsStoreDB? = null
        fun getInstance(context: Context): WingsStoreDB =
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                WingsStoreDB::class.java, "penjualan.db"
            ).build()
    }
}