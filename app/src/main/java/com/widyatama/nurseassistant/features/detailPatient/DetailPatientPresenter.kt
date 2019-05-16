package com.widyatama.nurseassistant.features.detailPatient

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.model.Patient
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class DetailPatientPresenter<V: DetailPatienViewContracts>
constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), DetailPatientPresenterContracts<V> {

    override fun getInformation(noRM: String) {
        val patient = Patient("008", "Chandra Abdul Fattah", true, 23,
                "Jl. Buahdua Sanca No. 1 RT 004 RW 002 Kecamatan Buahdua Kabupaten Sumedang",
                "081546415204")
        view?.showInformation(patient)
    }

    override fun getRiwayatPenyakit(limit: Int) {
        val allRiwayat = ArrayList<RiwayatPenyakit>()

        for (counter in 0..10){
            val riwayat = RiwayatPenyakit("Riwayat $counter", "${2009 + counter}")
            allRiwayat.add(riwayat)
        }

        view?.showRiwayat(allRiwayat)
    }
}