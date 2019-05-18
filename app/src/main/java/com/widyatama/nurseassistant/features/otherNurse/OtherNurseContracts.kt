package com.widyatama.nurseassistant.features.otherNurse

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

import com.widyatama.nurseassistant.data.model.Nurse

interface OtherNurseViewContracts: BaseFragmentContract {
    fun showNurse(values: ArrayList<Nurse>)

    fun listError()
}

interface OtherNursePresenterContracts<V: OtherNurseViewContracts>: BasePresenterContract<V> {
    fun getAllNurse()
}