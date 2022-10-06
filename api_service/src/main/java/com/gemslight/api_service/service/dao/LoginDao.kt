package com.gemslight.api_service.service.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gemslight.common.entity.LoginEntity


@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(login: LoginEntity)

    @Query("SELECT * from login WHERE user = :user")
    fun getUser(user: String): LiveData<LoginEntity>

}