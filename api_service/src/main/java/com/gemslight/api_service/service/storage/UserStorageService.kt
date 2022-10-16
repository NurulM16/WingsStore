package com.gemslight.api_service.service.storage

import com.gemslight.common.entity.LoginEntity

interface UserStorageService {
    fun login(loginEntity: LoginEntity)
    fun isLoggedIn() : Boolean
    fun getUser() : String
}