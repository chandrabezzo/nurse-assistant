package com.widyatama.nurseassistant.features.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.widyatama.core.base.BaseActivity;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.features.healingPlan.HealingPlanFragment;
import com.widyatama.nurseassistant.features.jadwal.JadwalFragment;
import com.widyatama.nurseassistant.features.otherNurse.OtherNurseFragment;
import com.widyatama.nurseassistant.features.profile.ProfileFragment;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends BaseActivity implements MainViewContracts {

    private MainPresenter<MainViewContracts> presenter;

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        presenter = new MainPresenter<MainViewContracts>(new SchedulerProviderUtil(),
                new CompositeDisposable());

        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationClick();
            }
        });

        BottomNavigationView bnvMain = findViewById(R.id.bnv_main);
        bnvMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_healing : {
                        launchFragment(R.id.fl_main, HealingPlanFragment.class);
                        break;
                    }
                    case R.id.nav_jadwal : {
                        launchFragment(R.id.fl_main, JadwalFragment.class);
                        break;
                    }
                    case R.id.nav_patient : {
                        launchFragment(R.id.fl_main, PatientFragment.class);
                        break;
                    }
                    case R.id.nav_nurse : {
                        launchFragment(R.id.fl_main, OtherNurseFragment.class);
                        break;
                    }
                    case R.id.nav_akun : {
                        launchFragment(R.id.fl_main, ProfileFragment.class);
                        break;
                    }
                    default: {
                        launchFragment(R.id.fl_main, HealingPlanFragment.class);
                        break;
                    }
                }

                return true;
            }
        });

        bnvMain.setSelectedItemId(R.id.nav_healing);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_main;
    }
}
