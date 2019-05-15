package com.widyatama.nurseassistant.features.patient

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class PatientPresenter<V: PatientViewContracts>
constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), PatientPresenterContracts<V> {
}