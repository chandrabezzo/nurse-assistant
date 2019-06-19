package com.widyatama.nurseassistant.features.profile

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.model.Profile
import io.reactivex.disposables.CompositeDisposable

class ProfilePresenter<V: ProfileViewContracts>
constructor(schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), ProfilePresenterContracts<V> {

    override fun getProfile() {
        val profile = Profile("Yang Zhen, A.Md. Kep.", "081546415204",
                "yanzhen@nurseasistant.com", "28 November 1995",
                "Jl. Babakan, RT 03 RW 10, Kp. Babakan, Desa Cigugur Girang, Kec. Parongpong," +
                        " Kab. Bandung Barat")
        view?.showProfile(profile)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}