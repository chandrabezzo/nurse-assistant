package com.widyatama.nurseassistant.data.network

import com.widyatama.nurseassistant.BuildConfig

/**
 * Created by bezzo on 25/09/17.
 */

object ApiEndPoint {
    const val USER = BuildConfig.BASE_URL + "user"
    const val JABATAN = BuildConfig.BASE_URL + "jabatan"
    const val KARYAWAN = BuildConfig.BASE_URL + "karyawan"
    const val SOCMED = BuildConfig.BASE_URL + "socialMedia"
    const val UNIV = BuildConfig.BASE_URL + "search"
}// This class is not publicly instantiable
