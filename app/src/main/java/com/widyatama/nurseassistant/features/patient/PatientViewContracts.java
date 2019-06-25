package com.widyatama.nurseassistant.features.patient;

import com.widyatama.core.base.BaseFragmentContract;
import com.widyatama.nurseassistant.data.model.Patient;

import java.util.List;

public interface PatientViewContracts extends BaseFragmentContract {
    void showPatient(List<Patient> values);

    void listError();
}
