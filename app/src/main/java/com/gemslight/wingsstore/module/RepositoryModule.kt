package com.gemslight.wingsstore.module

import com.gemslight.api_service.repository.CheckoutAndTransactionRepository
import com.gemslight.api_service.repository.LoginRepository
import com.gemslight.api_service.repository.ProductRepository
import com.gemslight.api_service.service.dao.CheckoutDao
import com.gemslight.api_service.service.dao.LoginDao
import com.gemslight.api_service.service.dao.ProductDao
import com.gemslight.api_service.service.storage.UserStorageService
import com.gemslight.common.ext.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideLoginRepository(
        loginDao: LoginDao,
        executors: AppExecutors,
        userStorageService: UserStorageService
    ) = LoginRepository(loginDao, executors, userStorageService)


    @Provides
    fun provideProductRepository(
        productDao: ProductDao,
        executors: AppExecutors,
    ) = ProductRepository(productDao, executors)

    @Provides
    fun provideCheckoutRepository(
        checkoutDao: CheckoutDao,
        executors: AppExecutors,
        userStorageService: UserStorageService
    ) = CheckoutAndTransactionRepository(checkoutDao, executors, userStorageService)
}