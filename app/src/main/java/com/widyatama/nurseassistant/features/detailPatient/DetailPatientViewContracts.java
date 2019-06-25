package com.widyatama.nurseassistant.features.detailPatient;

import com.widyatama.core.base.BaseActivityContract;
import com.widyatama.nurseassistant.data.model.Patient;
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit;

import java.util.List;

public interface DetailPatientViewContracts extends BaseActivityContract {
    void showInformation(Patient value);

    void showRiwayat(List<RiwayatPenyakit> values);
}
