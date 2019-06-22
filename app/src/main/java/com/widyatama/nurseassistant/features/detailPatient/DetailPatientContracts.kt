package com.widyatama.nurseassistant.features.detailPatient

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.Patient
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit

interface DetailPatienViewContracts: BaseActivityContract {
    fun showInformation(value: Patient)

    fun showRiwayat(values: List<RiwayatPenyakit>)
}

interface DetailPatientPresenterContracts<V: DetailPatienViewContracts>: BasePresenterContract<V> {
    fun getInformation(noRM: String)

    fun getRiwayatPenyakit(limit: Int)
}