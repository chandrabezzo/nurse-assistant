package com.widyatama.nurseassistant.features.jadwal;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;

import io.reactivex.disposables.CompositeDisposable;

public class JadwalPresenter<V extends JadwalViewContracts> extends BasePresenter<V> implements JadwalPresenterContracts<V> {

    LocalStorageHelper localStorage;
    SchedulerProviderUtil schedulerProvider;
    CompositeDisposable compositeDisposable;

    public JadwalPresenter(LocalStorageHelper localStorage, SchedulerProviderUtil schedulerProvider,
                           CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);

        this.localStorage = localStorage;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void getJadwal(int limit) {
        compositeDisposable.add(localStorage.getSampleDatabase().jadwal().getAll()
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe(jadwals -> {
                view.showJadwal(jadwals);
            }, throwable -> {
                System.out.println("Error get jadwal");
            }));
    }
}
