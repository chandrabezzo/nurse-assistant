package com.widyatama.nurseassistant.features.profile

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.Profile

interface ProfileViewContracts: BaseFragmentContract {
    fun enableEdit()

    fun disableEdit()

    fun showProfile(value: Profile)
}

interface ProfilePresenterContracts<V: ProfileViewContracts>: BasePresenterContract<V> {
    fun getProfile()
}