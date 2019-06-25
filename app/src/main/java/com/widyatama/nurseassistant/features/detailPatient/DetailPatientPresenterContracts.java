package com.widyatama.nurseassistant.features.detailPatient;

import com.widyatama.core.base.BasePresenterContract;

public interface DetailPatientPresenterContracts<V extends DetailPatientViewContracts> extends BasePresenterContract<V> {
    void getInformation(String noRm);

    void getRiwayatPenyakit(int limit);
}
