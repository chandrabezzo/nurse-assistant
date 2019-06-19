package com.widyatama.core.base;

import android.content.Context;

public interface BaseActivityContract extends BaseViewContract {

    Context getContext();

    void displayHome();

    void setActionBarTitle(String title);

    void onClickBack();

    void showProgressDialog(String message);

    void hideProgressDialog();
}
