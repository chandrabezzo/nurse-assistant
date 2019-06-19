package com.widyatama.core.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bezzo on 26/09/17.
 */

class SchedulerProviderUtil {

    fun <T> ioToMainFlowableScheduler(): FlowableTransformer<T, T> = FlowableTransformer { upstream ->
        upstream.subscribeOn(getIOThreadScheduler())
                .observeOn(getMainThreadScheduler())
    }


    private fun getIOThreadScheduler() = Schedulers.io()

    private fun getMainThreadScheduler() = AndroidSchedulers.mainThread()
}
