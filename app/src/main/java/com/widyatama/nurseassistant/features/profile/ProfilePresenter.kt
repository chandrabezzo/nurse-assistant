package com.widyatama.nurseassistant.features.profile

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.model.Profile
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class ProfilePresenter<V: ProfileViewContracts>
constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), ProfilePresenterContracts<V> {

    override fun getProfile() {
        val profile = Profile("Yang Zhen, A.Md. Kep.", "081546415204",
                "yanzhen@nurseasistant.com", "28 November 1995",
                "Jl. Babakan, RT 03 RW 10, Kp. Babakan, Desa Cigugur Girang, Kec. Parongpong," +
                        " Kab. Bandung Barat")
        view?.showProfile(profile)
    }
}