package com.widyatama.nurseassistant.view.fragment

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * Created by iman on 16/05/2019.
 */

class BottomAddPasienPresenter<V : BottomAddPasienViewContract>
constructor(private val apiHelper: ApiHelper, private val localHelper: LocalStorageHelper,
            sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper,
        schedulerProvider, compositeDisposable), BottomAddPasienPresenterContract<V> {

    override fun addPasien(value: Pasien) {
        println("=== insert")
        val exec =Executors.newSingleThreadExecutor()
        exec.execute {
            localHelper.sampleDatabase.pasien().insert(value)
        }
        println("=== done insert")
    }
}