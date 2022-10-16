package com.gemslight.wingsstore.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gemslight.api_service.repository.LoginRepository
import com.gemslight.api_service.service.storage.UserStorageService
import com.gemslight.common.base.AppResponse
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.LoginEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    val loginRepository: LoginRepository,
    private val userStorageService: UserStorageService
) : BaseViewModel(application) {
    var loginDataState = MutableLiveData<AppResponse<Boolean>>()
    val isLoggedIn = userStorageService.isLoggedIn()

    fun loginUser(loginEntity: LoginEntity) {
        viewModelScope.launch {
            loginDataState = loginRepository.getUser(loginEntity)
        }
    }

//    fun isUserLoggedIn() {
//        viewModelScope.launch {
//            isLoggedIn
//        }
//    }
}