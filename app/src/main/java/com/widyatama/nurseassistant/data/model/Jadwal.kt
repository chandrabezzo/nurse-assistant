package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.nurseassistant.constanta.AppConstans

@Entity(tableName = AppConstans.JADWAL)
data class Jadwal(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tanggal")
    val tanggal: Int,

    @ColumnInfo(name = "month")
    val month: Int,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "jam_mulai")
    val jamMulai: String,

    @ColumnInfo(name = "jam_selesai")
    val jamSelesai: String,

    @ColumnInfo(name = "ruangan")
    val ruangan: String
)