package com.widyatama.nurseassistant.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainViewContracts {

    val presenter: MainPresenter<MainViewContracts> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }
}
