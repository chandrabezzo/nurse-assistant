package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.nurseassistant.constanta.AppConstans

@Entity(tableName = AppConstans.RIWAYAT_PENYAKIT)
data class RiwayatPenyakit(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "tahun")
    val tahun: String
)