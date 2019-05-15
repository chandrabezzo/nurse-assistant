package com.widyatama.nurseassistant.features.detailPatient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import org.koin.android.ext.android.inject

class DetailPatientActivity : BaseActivity(), DetailPatienViewContracts {

    val presenter: DetailPatientPresenter<DetailPatienViewContracts> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_patient
    }
}
