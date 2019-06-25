package com.widyatama.nurseassistant.features.main;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MainViewContracts> extends BasePresenter<V> implements MainPresenterContracts<V> {

    public MainPresenter(SchedulerProviderUtil schedulerProvider, CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);
    }
}
