package com.widyatama.nurseassistant.features.jadwal

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

interface JadwalViewContracts: BaseFragmentContract {

}

interface JadwalPresenterContracts<V: JadwalViewContracts>: BasePresenterContract<V> {

}