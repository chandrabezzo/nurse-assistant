package com.widyatama.nurseassistant.features.login

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter<V: LoginViewContracts>
constructor(schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider,
        compositeDisposable), LoginPresenterContracts<V> {
}