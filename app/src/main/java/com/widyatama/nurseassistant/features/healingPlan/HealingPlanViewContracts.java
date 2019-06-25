package com.widyatama.nurseassistant.features.healingPlan;

import com.widyatama.core.base.BaseFragmentContract;
import com.widyatama.nurseassistant.data.model.HealingPlan;

import java.util.List;

public interface HealingPlanViewContracts extends BaseFragmentContract {
    void showHealing(List<HealingPlan> values);
}
