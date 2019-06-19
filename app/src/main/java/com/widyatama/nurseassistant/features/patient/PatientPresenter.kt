package com.widyatama.nurseassistant.features.patient

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.model.Patient
import io.reactivex.disposables.CompositeDisposable

class PatientPresenter<V: PatientViewContracts>
constructor(schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), PatientPresenterContracts<V> {

    override fun getAllPatient(limit: Int) {
        val allPatient = ArrayList<Patient>()

        val pasien1 = Patient("1", "Sobariah", false,
                20, "Jalan Mengger Asih No 15 Bandung", "087822309109")
        val pasien2 = Patient("2", "Nengsih Ulandari", false,
                27, "Jalan Ujung Berung No 19 Bandung", "0868226106609")
        val pasien3 = Patient("3", "Ahmad Junaedi", true,
                50, "Jalan Melong Asih No 100 Cimahi", "086590010910")
        val pasien4 = Patient("4", "Nuvo Arianto", true,
                30, "Jalan Cihampelas No 27 Cimahi", "082145678900")
        val pasien5 = Patient("5", "Shintya Purnama Sari", false,
                20, "Jalan Kawaluyaan No 90 Sumedang", "085789001234")
        val pasien6 = Patient("6", "Dama Umario", true,
                21, "Jalan Karang Tawulan No 80 Tasikmalaya", "081567819001")
        val pasien7 = Patient("7", "Gena Arum", false,
                20, "Jalan Gegerkalong No 78 Bandung", "081325990011")
        val pasien8 = Patient("8", "Sabar Sobariah", false,
                19, "Jalan Manon Jaya No 67 Garut", "089778901001")
        val pasien9 = Patient("9", "Siti Saedah", false,
                23, "Jalan Cikoneng No 78 Balaendah", "085678009660")

        allPatient.add(pasien1)
        allPatient.add(pasien2)
        allPatient.add(pasien3)
        allPatient.add(pasien4)
        allPatient.add(pasien5)
        allPatient.add(pasien6)
        allPatient.add(pasien7)
        allPatient.add(pasien8)
        allPatient.add(pasien9)
        view?.showPatient(allPatient)
    }
}