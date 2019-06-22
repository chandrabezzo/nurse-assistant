package com.widyatama.nurseassistant.features.otherNurse

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.model.Nurse
import io.reactivex.disposables.CompositeDisposable

class OtherNursePresenter<V: OtherNurseViewContracts>
constructor(private var localStorage: LocalStorageHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), OtherNursePresenterContracts<V> {
    override fun getAllNurse() {
        compositeDisposable.add(localStorage.sampleDatabase.nurse().getAll()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showNurse(it)
                }, {
                    print("get all nurse")
                }))
    }
}

