package com.widyatama.nurseassistant.features.detailPatient

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recyclerView.RiwayatPenyakitRVAdapter
import com.widyatama.nurseassistant.constanta.AppConstans
import com.widyatama.nurseassistant.data.model.Patient
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit
import kotlinx.android.synthetic.main.activity_detail_patient.*
import org.koin.android.ext.android.inject

class DetailPatientActivity : BaseActivity(), DetailPatienViewContracts {

    val presenter: DetailPatientPresenter<DetailPatienViewContracts> by inject()
    val adapter: RiwayatPenyakitRVAdapter by inject()
    val list = ArrayList<RiwayatPenyakit>()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        setActionBarTitle(getString(R.string.detail_patient))
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        dataReceived?.getString(AppConstans.PATIENT)?.let { presenter.getInformation(it) }

        val layoutManager = LinearLayoutManager(this)
        rv_riwayat_penyakit.layoutManager = layoutManager
        rv_riwayat_penyakit.adapter = adapter
        presenter.getRiwayatPenyakit(20)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_patient
    }

    override fun showInformation(value: Patient) {
        tv_nama.text = value.nama
        tv_age.text = "${value.umur} ${getString(R.string.umur_tahun)}"
        tv_phone.text = value.phoneNumber
        tv_alamat.text = value.alamat
    }

    override fun showRiwayat(values: ArrayList<RiwayatPenyakit>) {
        list.clear()
        list.addAll(values)

        adapter.setItem(list)
        adapter.notifyDataSetChanged()
    }
}
