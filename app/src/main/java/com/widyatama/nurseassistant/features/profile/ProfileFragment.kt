package com.widyatama.nurseassistant.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.R
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment(), ProfileViewContracts {

    val presenter: ProfilePresenter<ProfileViewContracts> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_profile
    }
}
