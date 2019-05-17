package com.widyatama.nurseassistant.features.login

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

interface LoginViewContracts: BaseActivityContract {

}

interface LoginPresenterContracts<V: LoginViewContracts>: BasePresenterContract<V> {

}