package com.widyatama.nurseassistant.features.measurement

import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import org.koin.android.ext.android.inject

class MeasurementActivity : BaseActivity(), MeasurementViewContracts {

    val presenter: MeasurementPresenter<MeasurementViewContracts> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_measurement
    }
}
