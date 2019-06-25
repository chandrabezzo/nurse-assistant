package com.widyatama.nurseassistant.view.activity.listPasien;

import android.content.Context;

import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.MvpApp;
import com.widyatama.nurseassistant.data.local.sampleDB.dao.PasienDao;
import com.widyatama.nurseassistant.util.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ListPasienPresenter implements BasePresenter<ListPasienViewContract> {

    private Context context;
    private ListPasienViewContract listPasienViewContract;
    private CompositeDisposable compositeDisposable;
    private SchedulerProviderUtil schedulerProviderUtil;

    public ListPasienPresenter(Context context, ListPasienViewContract listPasienViewContract){
        this.context = context;
        this.listPasienViewContract = listPasienViewContract;
        MvpApp.appComponent.inject(this);
        compositeDisposable = new CompositeDisposable();
        schedulerProviderUtil = new SchedulerProviderUtil();
    }

    @Inject
    PasienDao pasienDao;


    public void getList(){
        compositeDisposable.add(pasienDao.getAll().compose(schedulerProviderUtil.ioToMainFlowableScheduler()).subscribe(it ->
                listPasienViewContract.showPasien(it), throwable -> System.out.println("Error")));
//        ExecutorService exec = Executors.newSingleThreadExecutor();
//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//                listPasienViewContract.showPasien(userDao.getAll());
//            }
//        });


    }



    @Override
    public void onDetach() {

    }
}
