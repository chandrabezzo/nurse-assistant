package com.widyatama.nurseassistant.features.detailPatient

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.model.Patient
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit
import io.reactivex.disposables.CompositeDisposable

class DetailPatientPresenter<V: DetailPatienViewContracts>
constructor(private var localStorage: LocalStorageHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), DetailPatientPresenterContracts<V> {

    override fun getInformation(noRM: String) {
        compositeDisposable.add(localStorage.sampleDatabase.patient().get(noRM)
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showInformation(it)
                }, {
                    print("Error get information")
                }))
    }

    override fun getRiwayatPenyakit(limit: Int) {
        compositeDisposable.add(localStorage.sampleDatabase.riwayatPenyakit().getAll()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showRiwayat(it)
                }, {
                    print("error get riwayat penyakit")
                }))
    }
}