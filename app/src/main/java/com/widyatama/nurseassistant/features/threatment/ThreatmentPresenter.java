package com.widyatama.nurseassistant.features.threatment;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;

import io.reactivex.disposables.CompositeDisposable;

public class ThreatmentPresenter<V extends ThreatmentViewContracts> extends BasePresenter<V>
        implements ThreatmentPresenterContracts<V> {

    public ThreatmentPresenter(SchedulerProviderUtil schedulerProvider, CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);
    }
}
