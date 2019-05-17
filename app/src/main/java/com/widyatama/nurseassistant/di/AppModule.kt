
package com.widyatama.nurseassistant.di

import com.google.gson.Gson
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.adapter.recycleview.PasienRVAdapter
import com.widyatama.nurseassistant.adapter.recycleview.TodoRVdapter
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.network.ApiHelper
import com.widyatama.nurseassistant.view.activity.detailTodo.DetailTodoPresenter
import com.widyatama.nurseassistant.view.activity.detailTodo.DetailTodoViewContract
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienPresenter
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienViewContract
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienPresenter
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienViewContract
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appModule = module {
    single { LocalStorageHelper(androidApplication()) }
    single { SessionHelper() }
    factory { CompositeDisposable() }
    single { Gson() }
    single { SchedulerProviderUtil() }
    single { ApiHelper(get()) }
}

val presenterModule = module {
    factory { ListPasienPresenter<ListPasienViewContract>(get(), get(), get(), get(), get()) }
    factory { BottomAddPasienPresenter<BottomAddPasienViewContract>(get(), get(), get(), get(), get()) }
    factory { DetailTodoPresenter<DetailTodoViewContract>(get(), get(), get(), get(), get()) }
}

val rvAdapterModule = module {
    factory { PasienRVAdapter(get(), ArrayList()) }
    factory { TodoRVdapter(get(), ArrayList()) }
}

val spAdapterModule = module {

}

val allModule = listOf(appModule, presenterModule, rvAdapterModule, spAdapterModule)


