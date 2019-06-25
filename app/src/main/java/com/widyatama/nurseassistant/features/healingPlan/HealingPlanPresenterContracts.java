package com.widyatama.nurseassistant.features.healingPlan;

import com.widyatama.core.base.BasePresenterContract;

public interface HealingPlanPresenterContracts<V extends HealingPlanViewContracts> extends BasePresenterContract<V> {
    void getHealing(int limit);
}
