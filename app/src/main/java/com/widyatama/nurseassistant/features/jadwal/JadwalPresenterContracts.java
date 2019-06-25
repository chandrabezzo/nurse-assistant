package com.widyatama.nurseassistant.features.jadwal;

import com.widyatama.core.base.BasePresenterContract;

public interface JadwalPresenterContracts<V extends JadwalViewContracts> extends BasePresenterContract<V> {
    void getJadwal(int limit);
}
