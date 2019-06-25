package com.widyatama.nurseassistant.features.otherNurse;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;

import io.reactivex.disposables.CompositeDisposable;

public class OtherNursePresenter<V extends OtherNurseViewContracts> extends BasePresenter<V>
    implements OtherNursePresenterContracts<V> {

    LocalStorageHelper localStorage;
    SchedulerProviderUtil schedulerProvider;
    CompositeDisposable compositeDisposable;

    public OtherNursePresenter(LocalStorageHelper localStorage, SchedulerProviderUtil schedulerProvider,
                               CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);

        this.localStorage = localStorage;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void getAllNurse() {
        compositeDisposable.add(localStorage.getSampleDatabase().nurse().getAll()
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe(nurses -> {
                view.showNurse(nurses);
            }, throwable -> System.out.println("Eror get all nurse")));
    }
}
