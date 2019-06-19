package com.widyatama.nurseassistant.features.splash

import android.os.Bundle
import android.os.Handler
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.features.login.LoginActivity

class SplashActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        Handler().postDelayed({
            launchActivityClearAllStack(LoginActivity::class.java)
        },3000)
    }

    override fun setLayout(): Int {
        return R.layout.activity_splash
    }
}
