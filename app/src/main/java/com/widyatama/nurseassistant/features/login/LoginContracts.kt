package com.widyatama.nurseassistant.features.login

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.Profile

interface LoginViewContracts: BaseActivityContract {
    fun loginSuccess()

    fun loginFailed()
}

interface LoginPresenterContracts<V: LoginViewContracts>: BasePresenterContract<V> {
    fun saveProfile()

    fun saveAllPatient()

    fun saveOtherNurse()

    fun saveJadwal()

    fun saveHealingPlan()

    fun saveRiwayatPenyakit()

    fun saveAccount()

    fun login(username: String, password: String)
}

