package com.widyatama.nurseassistant.features.healingPlan

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.HealingPlan

interface HealingPlanViewContracts: BaseFragmentContract {
    fun showHealing(values: List<HealingPlan>)
}

interface HealingPlanPresenterContracts<V: HealingPlanViewContracts>: BasePresenterContract<V> {
    fun getHealing(limit: Int)
}