package com.widyatama.nurseassistant.features.login;

import com.widyatama.core.base.BaseActivityContract;

public interface LoginViewContracts extends BaseActivityContract {
    void loginSuccess();

    void loginFailed();
}
