package com.widyatama.nurseassistant.view.activity.detailTodo;

import android.content.Context;

import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.MvpApp;
import com.widyatama.nurseassistant.data.local.sampleDB.dao.PasienDao;
import com.widyatama.nurseassistant.util.BasePresenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailTodoPresenter implements BasePresenter<DetailTodoViewContract> {
    private Context context;
    private DetailTodoViewContract detailTodoViewContract;
    private CompositeDisposable compositeDisposable;
    private SchedulerProviderUtil schedulerProviderUtil;

    public DetailTodoPresenter(Context context, DetailTodoViewContract detailTodoViewContract){
        this.context = context;
        this.detailTodoViewContract = detailTodoViewContract;
        MvpApp.appComponent.inject(this);
        compositeDisposable = new CompositeDisposable();
        schedulerProviderUtil = new SchedulerProviderUtil();
    }

    @Inject
    PasienDao pasienDao;


    public void getList(int id){
        compositeDisposable.add(pasienDao.getById(id).compose(schedulerProviderUtil.ioToMainFlowableScheduler()).subscribe(it ->
                detailTodoViewContract.showResult(it), throwable -> System.out.println("Error")));
    }

    public void delete(int id){
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                pasienDao.delete(id);
            }
        });
    }



    @Override
    public void onDetach() {

    }
}
