package com.widyatama.nurseassistant.features.login

import android.os.Bundle
import android.widget.Toast
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.util.CommonUtil
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.features.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity(), LoginViewContracts {

    val presenter: LoginPresenter<LoginViewContracts> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        presenter.saveProfile()
        presenter.saveAllPatient()
        presenter.saveHealingPlan()
        presenter.saveJadwal()
        presenter.saveOtherNurse()
        presenter.saveRiwayatPenyakit()
        presenter.saveAccount()

        mb_login.setOnClickListener {
            presenter.login(et_username.text.toString(), et_password.text.toString())
        }

        iv_indonesia.setOnClickListener {
            CommonUtil.changeLanguage(this, "in")
            recreate()
        }

        iv_english.setOnClickListener {
            CommonUtil.changeLanguage(this, "en")
            recreate()
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_login
    }

    override fun loginSuccess() {
        launchActivityClearAllStack(MainActivity::class.java)
    }

    override fun loginFailed() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
    }
}
