package com.widyatama.nurseassistant.features.healingPlan

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

interface HealingPlanViewContracts: BaseFragmentContract {

}

interface HealingPlanPresenterContracts<V: HealingPlanViewContracts>: BasePresenterContract<V> {

}