package com.widyatama.nurseassistant.features.measurement

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

interface MeasurementViewContracts: BaseActivityContract {

}

interface MeasurementPresenterContracts<V: MeasurementViewContracts>: BasePresenterContract<V> {

}