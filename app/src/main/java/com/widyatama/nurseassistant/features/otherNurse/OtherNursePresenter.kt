package com.widyatama.nurseassistant.features.otherNurse

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.model.Nurse
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class OtherNursePresenter<V: OtherNurseViewContracts>
constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), OtherNursePresenterContracts<V> {
    override fun getAllNurse() {
        val allNurse = ArrayList<Nurse>()

        val nurse1 = Nurse("1", "Cinta Asih", "089678901001")
        val nurse2 = Nurse("2", "Raisa Salsabila", "089666678900")
        val nurse3 = Nurse("3", "Yura Agustina", "087822456789")
        val nurse4 = Nurse("4", "Agus Prihatin", "089678901011")
        val nurse5 = Nurse("5", "Pambudi Cahyo", "08100901001")
        val nurse6 = Nurse("6", "San Pratama", "089678901001")
        val nurse7 = Nurse("7", "Cita Amelia", "089567901001")
        val nurse8 = Nurse("8", "Arduino Ismail", "089678900011")
        val nurse9 = Nurse("9", "Excel Budiman", "089678902550")
        val nurse10 = Nurse("10", "Sudirman Said", "081450019001")

        allNurse.add(nurse1)
        allNurse.add(nurse2)
        allNurse.add(nurse3)
        allNurse.add(nurse4)
        allNurse.add(nurse5)
        allNurse.add(nurse6)
        allNurse.add(nurse7)
        allNurse.add(nurse8)
        allNurse.add(nurse9)
        allNurse.add(nurse10)

        view?.showNurse(allNurse)
    }
}

