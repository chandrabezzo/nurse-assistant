package com.widyatama.core.base;

import com.androidnetworking.error.ANError;

public interface BasePresenterContract<V extends BaseViewContract> {
    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();

    void logout();

    void clearLog();

    void logging(String message);
}
