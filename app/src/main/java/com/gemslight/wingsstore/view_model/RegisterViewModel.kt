package com.gemslight.wingsstore.view_model

import android.app.Application
import com.gemslight.api_service.repository.LoginRepository
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.LoginEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
    private val loginRepository: LoginRepository
) : BaseViewModel(application) {
    fun registerUser(loginEntity: LoginEntity) {
        loginRepository.insertUser(loginEntity)
    }
}