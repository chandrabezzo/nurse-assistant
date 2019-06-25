package com.widyatama.nurseassistant.features.otherNurse;

import com.widyatama.core.base.BasePresenterContract;

public interface OtherNursePresenterContracts<V extends OtherNurseViewContracts> extends BasePresenterContract<V> {
    void getAllNurse();
}
