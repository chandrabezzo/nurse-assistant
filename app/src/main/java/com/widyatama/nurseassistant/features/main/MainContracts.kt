package com.widyatama.nurseassistant.features.main

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

interface MainViewContracts: BaseActivityContract {

}

interface MainPresenterContracts<V: MainViewContracts>: BasePresenterContract<V> {

}