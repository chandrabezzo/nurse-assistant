package com.widyatama.nurseassistant.data.model

import java.sql.Time

data class Jadwal(
    val tanggal: Int,
    val month: Int,
    val year: Int,
    val jamMulai: String,
    val jamSelesai: String,
    val ruangan: String
)