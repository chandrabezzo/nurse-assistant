package com.widyatama.nurseassistant.features.detailPatient;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;

import io.reactivex.disposables.CompositeDisposable;

public class DetailPatientPresenter<V extends DetailPatientViewContracts> extends BasePresenter<V>
    implements DetailPatientPresenterContracts<V> {

    private SchedulerProviderUtil schedulerProvider;
    private CompositeDisposable compositeDisposable;
    private LocalStorageHelper localStorage;

    public DetailPatientPresenter(LocalStorageHelper localStorage, SchedulerProviderUtil schedulerProvider,
                                  CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);

        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.localStorage = localStorage;
    }

    @Override
    public void getInformation(String noRm) {
        compositeDisposable.add(localStorage.getSampleDatabase().patient().get(noRm)
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe(patient -> {
                    view.showInformation(patient);
                }, throwable -> {
                    System.out.println("Error get Information");
                }));
    }

    @Override
    public void getRiwayatPenyakit(int limit) {
        compositeDisposable.add(localStorage.getSampleDatabase().riwayatPenyakit().getAll()
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe(riwayatPenyakits -> {
                view.showRiwayat(riwayatPenyakits);
            }, throwable -> System.out.println("Error get Riwayat Penyakit")));
    }
}
