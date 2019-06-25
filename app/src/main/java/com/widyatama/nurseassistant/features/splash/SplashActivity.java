package com.widyatama.nurseassistant.features.splash;

import android.os.Bundle;
import android.os.Handler;

import com.widyatama.core.base.BaseActivity;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.features.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchActivityClearAllStack(LoginActivity.class);
            }
        }, 3000);
    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_splash;
    }
}
