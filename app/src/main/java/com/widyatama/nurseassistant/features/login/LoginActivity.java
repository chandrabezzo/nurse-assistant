package com.widyatama.nurseassistant.features.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatImageView;

import com.widyatama.core.base.BaseActivity;
import com.widyatama.core.util.CommonUtils;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.features.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginViewContracts {

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        Button button = (Button) findViewById(R.id.mb_login);
        AppCompatImageView btnID = (AppCompatImageView) findViewById(R.id.iv_indonesia);
        AppCompatImageView btnEN = (AppCompatImageView) findViewById(R.id.iv_english);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityClearAllStack(MainActivity.class);
            }
        });
        btnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.changeLanguage(LoginActivity.this, "in");
            }
        });
        btnEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.changeLanguage(LoginActivity.this, "en");
            }
        });

    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        
    }

    @Override
    public void loginFailed() {

    }
}
