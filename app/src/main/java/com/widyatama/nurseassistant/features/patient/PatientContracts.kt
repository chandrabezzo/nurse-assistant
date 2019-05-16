package com.widyatama.nurseassistant.features.patient

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.Patient

interface PatientViewContracts: BaseFragmentContract {
    fun showPatient(values: ArrayList<Patient>)
}

interface PatientPresenterContracts<V: PatientViewContracts>: BasePresenterContract<V> {
    fun getAllPatient(limit: Int)
}