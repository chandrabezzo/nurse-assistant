package com.widyatama.nurseassistant.features.login

import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity(), LoginViewContracts {

    val presenter: LoginPresenter<LoginViewContracts> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_login
    }
}
