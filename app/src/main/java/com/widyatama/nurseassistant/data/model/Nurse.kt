package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.nurseassistant.constanta.AppConstans

@Entity(tableName = AppConstans.NURSE)
data class Nurse(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nip")
    val nip: String,

    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String
)