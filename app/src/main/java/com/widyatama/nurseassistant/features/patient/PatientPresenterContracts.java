package com.widyatama.nurseassistant.features.patient;

import com.widyatama.core.base.BasePresenterContract;

public interface PatientPresenterContracts<V extends PatientViewContracts> extends BasePresenterContract<V> {
    void getAllPatient(int limit);
}
