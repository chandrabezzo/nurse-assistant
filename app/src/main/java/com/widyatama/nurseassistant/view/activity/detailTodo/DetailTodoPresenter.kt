package com.widyatama.nurseassistant.view.activity.detailTodo

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors

class DetailTodoPresenter<V: DetailTodoViewContract>
constructor(private val localHelper: LocalStorageHelper,
            schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(
        schedulerProvider, compositeDisposable), DetailTodoPresentContract<V> {

    override fun getList(id: Int) {
        compositeDisposable.add(localHelper.sampleDatabase.pasien().getById(id)
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showResult(it)
                }, {
                    logging(it.toString())
                }))
    }

    override fun delete(id: Int) {
        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localHelper.sampleDatabase.pasien().delete(id)
        }
    }
}
