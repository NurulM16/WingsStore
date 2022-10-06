package com.gemslight.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "login")
class LoginEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "user")
    val user: String,
    @field:ColumnInfo(name = "password")
    val password: String
)



