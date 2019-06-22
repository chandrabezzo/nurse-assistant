package com.widyatama.nurseassistant.features.healingPlan

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.model.HealingPlan
import io.reactivex.disposables.CompositeDisposable

class HealingPlanPresenter<V: HealingPlanViewContracts>
constructor(private var localStorage: LocalStorageHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), HealingPlanPresenterContracts<V> {

    override fun getHealing(limit: Int) {
        compositeDisposable.add(localStorage.sampleDatabase.healingPlan().getAll()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showHealing(it)
                }, {
                    print("error get Healing")
                }))
    }
}