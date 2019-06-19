package com.widyatama.nurseassistant.features.healingPlan

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.HealingPlan
import io.reactivex.disposables.CompositeDisposable

class HealingPlanPresenter<V: HealingPlanViewContracts>
constructor(schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), HealingPlanPresenterContracts<V> {

    override fun getHealing(limit: Int) {
        val allHealing = ArrayList<HealingPlan>()

        for (counter in 0..10){
            val healing = HealingPlan(1, "Pengecekan Glukosa", "${counter*10} mg/dL",
                    R.drawable.ic_stethoscope_black)
            allHealing.add(healing)
        }

        view?.showHealing(allHealing)
    }
}