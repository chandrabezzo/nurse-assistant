package com.widyatama.nurseassistant.features.threatment

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class ThreatmentPresenter<V: ThreatmentViewContract>
constructor(schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), ThreatmentPresentContract<V> {

    override fun getList() {
        // todo
    }
}