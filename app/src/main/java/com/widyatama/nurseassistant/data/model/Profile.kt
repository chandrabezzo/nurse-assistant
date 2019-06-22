package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.nurseassistant.constanta.AppConstans

@Entity(tableName = AppConstans.PROFILE)
data class Profile(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "birthday")
    val birthDay: String,

    @ColumnInfo(name = "address")
    val address: String
)