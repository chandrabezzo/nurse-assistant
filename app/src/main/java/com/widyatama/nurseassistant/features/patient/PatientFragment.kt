package com.widyatama.nurseassistant.features.patient

import android.os.Bundle
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.R
import org.koin.android.ext.android.inject

class PatientFragment : BaseFragment(), PatientViewContracts {

    val presenter: PatientPresenter<PatientViewContracts> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_patient
    }
}
