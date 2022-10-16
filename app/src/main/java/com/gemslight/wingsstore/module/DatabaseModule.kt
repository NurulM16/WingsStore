package com.gemslight.wingsstore.module

import android.content.Context
import com.gemslight.api_service.service.database.WingsStoreDB
import com.gemslight.api_service.service.storage.UserStorageService
import com.gemslight.api_service.service.storage.UserStorageServiceSharedPreferences
import com.gemslight.common.ext.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = WingsStoreDB.getInstance(context)

    @Provides
    @Singleton
    fun provideLoginDao(wingsStoreDB: WingsStoreDB) = wingsStoreDB.loginDao()

    @Provides
    @Singleton
    fun provideProductDao(wingsStoreDB: WingsStoreDB) = wingsStoreDB.productDao()

    @Provides
    @Singleton
    fun provideCheckoutDao(wingsStoreDB: WingsStoreDB) = wingsStoreDB.checkoutDao()

    @Provides
    @Singleton
    fun provideUserStorageServiceSharedPreferences(@ApplicationContext context: Context): UserStorageService = UserStorageServiceSharedPreferences(context)

    @Provides
    @Singleton
    fun providesExecutor() = AppExecutors()
}