package com.widyatama.nurseassistant.features.threatment

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class ThreatmentPresenter<V: ThreatmentViewContract>
constructor(private val apiHelper: ApiHelper, private val localHelper: LocalStorageHelper,
            sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), ThreatmentPresentContract<V> {

    override fun getList() {
        // todo
    }
}