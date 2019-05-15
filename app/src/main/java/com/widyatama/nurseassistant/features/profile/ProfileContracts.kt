package com.widyatama.nurseassistant.features.profile

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

interface ProfileViewContracts: BaseFragmentContract {

}

interface ProfilePresenterContracts<V: ProfileViewContracts>: BasePresenterContract<V> {

}