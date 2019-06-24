package com.widyatama.nurseassistant.view.fragment;

import android.content.Context;

import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.MvpApp;
import com.widyatama.nurseassistant.data.local.sampleDB.dao.PasienDao;
import com.widyatama.nurseassistant.data.model.Pasien;
import com.widyatama.nurseassistant.util.BasePresenter;
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienViewContract;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BottomAddPasienPresenter implements BasePresenter<BottomAddPasienViewContract> {

    private Context context;
    private BottomAddPasienViewContract bottomAddPasienViewContract;
    private CompositeDisposable compositeDisposable;
    private SchedulerProviderUtil schedulerProviderUtil;

    @Inject
    PasienDao pasienDao;

    public BottomAddPasienPresenter(Context context, BottomAddPasienViewContract bottomAddPasienViewContract){
        this.context = context;
        this.bottomAddPasienViewContract = bottomAddPasienViewContract;
        MvpApp.appComponent.inject(this);
        compositeDisposable = new CompositeDisposable();
        schedulerProviderUtil = new SchedulerProviderUtil();
    }

    public void addPasien(Pasien pasien){
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                pasienDao.insert(pasien);
            }
        });
    }

    @Override
    public void onDetach() {

    }
}
