package com.widyatama.nurseassistant.features.jadwal

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import io.reactivex.disposables.CompositeDisposable

class JadwalPresenter<V: JadwalViewContracts> constructor(private var localStorage: LocalStorageHelper,
  schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(schedulerProvider, compositeDisposable), JadwalPresenterContracts<V> {

    override fun getJadwal(limit: Int) {
        compositeDisposable.add(localStorage.sampleDatabase.jadwal().getAll()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showJadwal(it)
                }, {
                    print("error get jadwal")
                }))
    }
}