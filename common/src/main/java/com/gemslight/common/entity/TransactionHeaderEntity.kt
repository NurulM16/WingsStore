package com.gemslight.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "transaction_header")
class TransactionHeaderEntity(
    @field:ColumnInfo(name = "document_code")
    val documentCode: String,
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "document_number")
    val documentNumber: Int,
    @field:ColumnInfo(name = "user")
    val user: String,
    @field:ColumnInfo(name = "total")
    val total: Double,
    @field:ColumnInfo(name = "date")
    val date: String
)