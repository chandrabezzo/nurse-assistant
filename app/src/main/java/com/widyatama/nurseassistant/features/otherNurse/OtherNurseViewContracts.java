package com.widyatama.nurseassistant.features.otherNurse;

import com.widyatama.core.base.BaseFragmentContract;
import com.widyatama.nurseassistant.data.model.Nurse;

import java.util.List;

public interface OtherNurseViewContracts extends BaseFragmentContract {
    void showNurse(List<Nurse> values);

    void listError();
}
