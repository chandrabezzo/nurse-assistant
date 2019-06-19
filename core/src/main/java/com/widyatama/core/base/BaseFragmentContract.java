package com.widyatama.core.base;

import android.content.Context;

public interface BaseFragmentContract extends BaseViewContract {
    Context getContext();

    void onBackPressed();

    void showProgressDialog(String message);

    void hideProgressDialog();
}
