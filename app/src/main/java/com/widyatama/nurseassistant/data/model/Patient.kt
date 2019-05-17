package com.widyatama.nurseassistant.data.model

data class Patient(
    val noRm: String,
    val nama: String,
    val gender: Boolean,
    val umur: Int,
    val alamat: String,
    val phoneNumber: String
)