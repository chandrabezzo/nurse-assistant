package com.widyatama.nurseassistant.view.activity.listPasien

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by iman on 16/05/2019.
 */

class ListPasienPresenter<V: ListPasienViewContract>
constructor(private val localHelper: LocalStorageHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider,
        compositeDisposable), ListPasienPresentContract<V> {

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

//    private fun setAlarm(time: Long) {
//        //getting the alarm manager
//        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager?
//
//        //creating a new intent specifying the broadcast receiver
//        val i = Intent(, MyAlarm::class.java)
//
//        //creating a pending intent using the intent
//        val pi = PendingIntent.getBroadcast(this, 0, i, 0)
//
//        //setting the repeating alarm that will be fired every day
//        am!!.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi)
//        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
//    }
}