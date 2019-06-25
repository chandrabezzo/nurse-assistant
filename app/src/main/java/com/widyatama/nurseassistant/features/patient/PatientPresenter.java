package com.widyatama.nurseassistant.features.patient;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;
import com.widyatama.nurseassistant.data.model.Patient;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class PatientPresenter<V extends PatientViewContracts> extends BasePresenter<V> implements PatientPresenterContracts<V> {

    LocalStorageHelper localStorage;

    public PatientPresenter(LocalStorageHelper localStorage, SchedulerProviderUtil schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(schedulerProvider, compositeDisposable);

        this.localStorage = localStorage;
    }

    @Override
    public void getAllPatient(int limit) {
        compositeDisposable.add(localStorage.getSampleDatabase().patient().getAll()
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe(patients -> {
                view.showPatient(patients);
            }, throwable -> System.out.println("Error get All Patient")));
    }
}
