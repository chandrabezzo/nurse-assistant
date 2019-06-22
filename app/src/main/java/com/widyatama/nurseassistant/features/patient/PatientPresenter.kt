package com.widyatama.nurseassistant.features.patient

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.model.Patient
import io.reactivex.disposables.CompositeDisposable

class PatientPresenter<V: PatientViewContracts>
constructor(private var localStorage: LocalStorageHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), PatientPresenterContracts<V> {

    override fun getAllPatient(limit: Int) {
        compositeDisposable.add(localStorage.sampleDatabase.patient().getAll()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showPatient(it)
                }, {
                    print("error get all patient")
                }))
    }
}