package com.widyatama.nurseassistant.view.activity.listPasien

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by iman on 16/05/2019.
 */

class ListPasienPresenter<V: ListPasienViewContract>
constructor(private val apiHelper: ApiHelper, private val localHelper: LocalStorageHelper,
            sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), ListPasienPresentContract<V> {

    override fun getList() {
        view?.showLoading()
        compositeDisposable.add(localHelper.sampleDatabase.pasien().getAll()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.hideLoading()
                    view?.showPasien(it)
                }, {
                    logging(it.toString())
                }))
    }
}