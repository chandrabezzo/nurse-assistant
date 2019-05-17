package com.widyatama.nurseassistant.features.detailPatient

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

interface DetailPatienViewContracts: BaseActivityContract {

}

interface DetailPatientPresenterContracts<V: DetailPatienViewContracts>: BasePresenterContract<V> {

}