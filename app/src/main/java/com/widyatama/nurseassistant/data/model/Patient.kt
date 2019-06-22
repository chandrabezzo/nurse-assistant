package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.nurseassistant.constanta.AppConstans

@Entity(tableName = AppConstans.PATIENT)
data class Patient(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "no_rm")
    val noRm: String,

    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "gender")
    val gender: Boolean,

    @ColumnInfo(name = "umur")
    val umur: Int,

    @ColumnInfo(name = "alamat")
    val alamat: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String
)