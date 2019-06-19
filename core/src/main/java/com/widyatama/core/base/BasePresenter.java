package com.widyatama.core.base;

import com.androidnetworking.error.ANError;
import com.widyatama.core.util.SchedulerProviderUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends BaseViewContract> implements BasePresenterContract<V> {

    public SchedulerProviderUtil schedulerProvider;
    public CompositeDisposable compositeDisposable;

    public BasePresenter(SchedulerProviderUtil schedulerProviderUtil,
                  CompositeDisposable compositeDisposable) {
        this.schedulerProvider = schedulerProviderUtil;
        this.compositeDisposable = compositeDisposable;
    }

    public V view;

    boolean isViewAttached(){
        return view != null;
    }

    @Override
    public void onAttach(@NotNull V mvpView) {
        view = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        view = null;
    }

    @Override
    public void handleApiError(@NotNull ANError error) {

    }

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public void clearLog() {

    }

    @Override
    public void logout() {
        clearLog();

    }

    @Override
    public void logging(@Nullable String message) {

    }
}
