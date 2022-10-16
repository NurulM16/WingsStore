package com.gemslight.api_service.service.storage

import android.content.Context
import android.preference.PreferenceManager
import com.gemslight.common.entity.LoginEntity

class UserStorageServiceSharedPreferences(val context: Context): UserStorageService {
    val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun login(user: LoginEntity) {
       preferences.edit().putBoolean(IS_LOGGED_IN, true).putString(USER, user.user).apply()
    }

    override fun isLoggedIn(): Boolean {
       return preferences.getBoolean(IS_LOGGED_IN, false)
    }

    override fun getUser(): String {
       return preferences.getString(USER, "") ?: ""
    }

    companion object Constants {
        const val IS_LOGGED_IN = "IS_LOGGED IN"
        const val USER = "USER"
    }
}