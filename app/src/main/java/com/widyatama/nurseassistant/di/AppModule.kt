
package com.widyatama.nurseassistant.di

import com.google.gson.Gson
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.adapter.recyclerView.JadwalRVAdapter
import com.widyatama.nurseassistant.adapter.recyclerView.OtherNurseRVAdapter
import com.widyatama.nurseassistant.adapter.recyclerView.PatientRVAdapter
import com.widyatama.nurseassistant.adapter.recyclerView.RiwayatPenyakitRVAdapter
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.network.ApiHelper
import com.widyatama.nurseassistant.features.detailPatient.DetailPatienViewContracts
import com.widyatama.nurseassistant.features.detailPatient.DetailPatientPresenter
import com.widyatama.nurseassistant.features.healingPlan.HealingPlanPresenter
import com.widyatama.nurseassistant.features.healingPlan.HealingPlanViewContracts
import com.widyatama.nurseassistant.features.jadwal.JadwalPresenter
import com.widyatama.nurseassistant.features.jadwal.JadwalViewContracts
import com.widyatama.nurseassistant.features.login.LoginPresenter
import com.widyatama.nurseassistant.features.login.LoginViewContracts
import com.widyatama.nurseassistant.features.main.MainPresenter
import com.widyatama.nurseassistant.features.main.MainViewContracts
import com.widyatama.nurseassistant.features.otherNurse.OtherNursePresenter
import com.widyatama.nurseassistant.features.otherNurse.OtherNurseViewContracts
import com.widyatama.nurseassistant.features.patient.PatientPresenter
import com.widyatama.nurseassistant.features.patient.PatientViewContracts
import com.widyatama.nurseassistant.features.profile.ProfilePresenter
import com.widyatama.nurseassistant.features.profile.ProfileViewContracts
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienPresenter
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienViewContract
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienPresenter
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienViewContract
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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
    factory { DetailPatientPresenter<DetailPatienViewContracts>(get(), get(), get(), get()) }
    factory { HealingPlanPresenter<HealingPlanViewContracts>(get(), get(), get(), get()) }
    factory { JadwalPresenter<JadwalViewContracts>(get(), get(), get(), get()) }
    factory { LoginPresenter<LoginViewContracts>(get(), get(), get(), get()) }
    factory { MainPresenter<MainViewContracts>(get(), get(), get(), get()) }
    factory { OtherNursePresenter<OtherNurseViewContracts>(get(), get(), get(), get()) }
    factory { PatientPresenter<PatientViewContracts>(get(), get(), get(), get()) }
    factory { ProfilePresenter<ProfileViewContracts>(get(), get(), get(), get()) }
}

val rvAdapterModule = module {
    factory { OtherNurseRVAdapter(androidContext(), ArrayList()) }
    factory { PatientRVAdapter(androidContext(), ArrayList()) }
    factory { RiwayatPenyakitRVAdapter(androidContext(), ArrayList()) }
    factory { JadwalRVAdapter(androidContext(), ArrayList()) }
}

val spAdapterModule = module {

}

val allModule = listOf(appModule, presenterModule, rvAdapterModule, spAdapterModule)


