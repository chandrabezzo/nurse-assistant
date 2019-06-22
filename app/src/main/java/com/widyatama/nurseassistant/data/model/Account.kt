package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.nurseassistant.constanta.AppConstans

@Entity(tableName = AppConstans.ACCOUNT)
data class Account(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "npm")
    val npm: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String
)