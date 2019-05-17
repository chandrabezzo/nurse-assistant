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

        for (counter in 0..50){
            val nurse = Nurse("$counter", "Nurse $counter", "081546415204")
            allNurse.add(nurse)
        }

        view?.showNurse(allNurse)
    }
}