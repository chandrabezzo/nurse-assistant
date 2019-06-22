package com.widyatama.nurseassistant.features.profile

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.model.Profile
import io.reactivex.disposables.CompositeDisposable
import java.util.function.Consumer

class ProfilePresenter<V: ProfileViewContracts>
constructor(private var localStorage: LocalStorageHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider, compositeDisposable),
        ProfilePresenterContracts<V> {

    override fun getProfile() {
        compositeDisposable.add(localStorage.sampleDatabase.profile().get()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showProfile(it)
                }, {
                    print("error get Profile")
                }))
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