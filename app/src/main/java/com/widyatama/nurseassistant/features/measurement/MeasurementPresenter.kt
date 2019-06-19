package com.widyatama.nurseassistant.features.measurement

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class MeasurementPresenter<V: MeasurementViewContracts>
constructor(schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), MeasurementPresenterContracts<V> {
}