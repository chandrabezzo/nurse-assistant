package com.widyatama.nurseassistant.features.main

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class MainPresenter<V: MainViewContracts>
constructor(schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), MainPresenterContracts<V> {
}