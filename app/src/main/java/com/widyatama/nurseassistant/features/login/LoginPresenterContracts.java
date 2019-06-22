package com.widyatama.nurseassistant.features.login;

import com.widyatama.core.base.BasePresenterContract;

public interface LoginPresenterContracts<V extends LoginViewContracts> extends BasePresenterContract<V> {
    void saveProfile();

    void saveAllPatient();

    void saveOtherNurse();

    void saveJadwal();

    void saveHealingPlan();

    void saveRiwayatPenyakit();

    void saveAccount();

    void login(String username, String password);
}
