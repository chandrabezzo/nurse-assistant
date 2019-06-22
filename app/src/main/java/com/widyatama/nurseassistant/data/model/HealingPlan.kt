package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.nurseassistant.constanta.AppConstans

@Entity(tableName = AppConstans.HEALING_PLAN)
data class HealingPlan(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "tindakan")
    val tindakan: String,

    @ColumnInfo(name = "deskripsi")
    val deskripsi: String,

    @ColumnInfo(name = "jenis")
    val jenis: Int
)