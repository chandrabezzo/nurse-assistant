package com.widyatama.nurseassistant.features.otherNurse


import android.os.Bundle
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.R
import org.koin.android.ext.android.inject

class OtherNurseFragment : BaseFragment(), OtherNurseViewContracts {

    val presenter: OtherNursePresenter<OtherNurseViewContracts> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_other_nurse
    }
}
