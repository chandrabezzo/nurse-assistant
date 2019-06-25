package com.widyatama.nurseassistant.features.measurement;

import android.os.Bundle;

import com.widyatama.core.base.BaseActivity;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;

import io.reactivex.disposables.CompositeDisposable;

public class MeasurementActivity extends BaseActivity implements MeasurementViewContracts {

    private MeasurementPresenter<MeasurementViewContracts> presenter;

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        presenter = new MeasurementPresenter<MeasurementViewContracts>(new SchedulerProviderUtil(),
            new CompositeDisposable());
        presenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_measurement;
    }
}
