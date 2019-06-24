package com.widyatama.nurseassistant.features.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.widyatama.core.base.BaseActivity;
import com.widyatama.core.util.CommonUtils;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;
import com.widyatama.nurseassistant.features.main.MainActivity;

import io.reactivex.disposables.CompositeDisposable;

public class LoginActivity extends BaseActivity implements LoginViewContracts {

    private LoginPresenter<LoginViewContracts> presenter;

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        presenter = new LoginPresenter<LoginViewContracts>(
                new LocalStorageHelper(this), new SchedulerProviderUtil(), new CompositeDisposable());

        presenter.onAttach(this);
        presenter.saveProfile();
        presenter.saveAllPatient();
        presenter.saveHealingPlan();
        presenter.saveJadwal();
        presenter.saveOtherNurse();
        presenter.saveRiwayatPenyakit();
        presenter.saveAccount();

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

        launchActivityClearAllStack(MainActivity.class);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        launchActivityClearAllStack(MainActivity.class);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
    }
}
