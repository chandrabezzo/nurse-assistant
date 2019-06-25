package com.widyatama.nurseassistant.features.measurement;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;

import io.reactivex.disposables.CompositeDisposable;

public class MeasurementPresenter<V extends MeasurementViewContracts> extends BasePresenter<V>
        implements MeasurementPresenterContracts<V> {

    public MeasurementPresenter(SchedulerProviderUtil schedulerProvider, CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);
    }
}
