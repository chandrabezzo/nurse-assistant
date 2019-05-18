package com.widyatama.nurseassistant.features.main

import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchFragment
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.features.healingPlan.HealingPlanFragment
import com.widyatama.nurseassistant.features.jadwal.JadwalFragment
import com.widyatama.nurseassistant.features.otherNurse.OtherNurseFragment
import com.widyatama.nurseassistant.features.patient.PatientFragment
import com.widyatama.nurseassistant.features.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainViewContracts {

    val presenter: MainPresenter<MainViewContracts> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_healing -> {
                    launchFragment(R.id.fl_main, HealingPlanFragment::class.java)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_jadwal -> {
                    launchFragment(R.id.fl_main, JadwalFragment::class.java)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_patient -> {
                    launchFragment(R.id.fl_main, PatientFragment::class.java)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_nurse -> {
                    launchFragment(R.id.fl_main, OtherNurseFragment::class.java)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_akun -> {
                    launchFragment(R.id.fl_main, ProfileFragment::class.java)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    launchFragment(R.id.fl_main, HealingPlanFragment::class.java)
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

        bnv_main.selectedItemId = R.id.nav_healing
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }
}