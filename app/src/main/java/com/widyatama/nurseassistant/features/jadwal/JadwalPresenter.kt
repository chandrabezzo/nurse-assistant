package com.widyatama.nurseassistant.features.jadwal

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable
import com.widyatama.nurseassistant.data.model.Jadwal

class JadwalPresenter<V: JadwalViewContracts>
constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), JadwalPresenterContracts<V> {

    override fun getJadwal(limit: Int) {
        val allJadwal = ArrayList<Jadwal>()

        for (counter in 15..25){
            var jamMulai = ""
            var jamSelesai = ""
            var jam = counter

            if (jam < 10){
                if (jam > 24) jam = 0

                jamMulai = "0$jam:00"
                jamSelesai = "${jam + 12}:00"
            }
            else {
                jamMulai = "$jam:00"

                if (jam > 12) jam = 0
                jamSelesai = "${jam + 12}:00"
            }

            val jadwal = Jadwal(counter, 5, 2019, jamMulai, jamSelesai, "Mawar")
            allJadwal.add(jadwal)
        }

        view?.showJadwal(allJadwal)
    }
}