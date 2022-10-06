package com.gemslight.api_service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gemslight.api_service.service.dao.LoginDao
import com.gemslight.common.base.AppResponse
import com.gemslight.common.entity.LoginEntity
import com.gemslight.common.ext.AppExecutors
import okhttp3.ResponseBody.Companion.toResponseBody

class LoginRepository(
    private val loginDao: LoginDao,
    private val appExecutors: AppExecutors
) {
    private val resultLogin = MediatorLiveData<AppResponse<Boolean>>()

    fun getUser(user: LoginEntity): LiveData<AppResponse<Boolean>> {
        val localData = loginDao.getUser(user.user)
        resultLogin.addSource(localData) {
            it
            when {

                it == null -> resultLogin.value =
                    AppResponse.error(null, "User not found".toResponseBody(), 400)
                it.password != user.password -> resultLogin.value =
                    AppResponse.error(null, "invalid password".toResponseBody(), 400)
                else -> resultLogin.value = AppResponse.success(true)
            }
        }
        return resultLogin
    }

    fun insertUser(user: LoginEntity) {
        appExecutors.diskIO.execute {
            loginDao.insertUser(user)
        }
    }


}