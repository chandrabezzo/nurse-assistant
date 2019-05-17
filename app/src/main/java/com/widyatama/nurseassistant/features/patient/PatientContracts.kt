package com.widyatama.nurseassistant.features.patient

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

interface PatientViewContracts: BaseFragmentContract {

}

interface PatientPresenterContracts<V: PatientViewContracts>: BasePresenterContract<V> {

}