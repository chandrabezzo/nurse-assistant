package com.widyatama.nurseassistant.features.healingPlan;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;

import io.reactivex.disposables.CompositeDisposable;

public class HealingPlanPresenter<V extends HealingPlanViewContracts> extends BasePresenter<V>
    implements HealingPlanPresenterContracts<V> {

    LocalStorageHelper localStorage;
    SchedulerProviderUtil schedulerProvider;
    CompositeDisposable compositeDisposable;

    public HealingPlanPresenter(LocalStorageHelper localStorage, SchedulerProviderUtil schedulerProvider,
                                CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);

        this.localStorage = localStorage;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void getHealing(int limit) {
        compositeDisposable.add(localStorage.getSampleDatabase().healingPlan().getAll()
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe(healingPlans -> {
                view.showHealing(healingPlans);
            }, throwable -> System.out.println("Error get healing")));
    }
}
