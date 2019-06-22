package com.widyatama.nurseassistant

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.androidnetworking.AndroidNetworking
import com.dwidasa.app.proline.AppComponent
import com.dwidasa.app.proline.DaggerAppComponent
import com.dwidasa.app.proline.di.DatabaseModule
import com.dwidasa.app.proline.di.MyAppModule
import com.widyatama.core.util.AppLoggerUtil
import com.widyatama.core.util.LocaleUtil
import com.widyatama.nurseassistant.di.allModule
import org.koin.android.ext.android.startKoin

/**
 * Created by bezzo on 11/01/18.
 */
class MvpApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleUtil.onAttach(base, LocaleUtil.getLanguage(base)))

        MultiDex.install(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
        val TAG : String = "FCM"
    }

    override fun onCreate() {
        super.onCreate()

//        startKoin(this, allModule)

        AppLoggerUtil.init()
        AndroidNetworking.initialize(applicationContext)
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .myAppModule(MyAppModule(this))
                .databaseModule(DatabaseModule(this)).build()

    }
}