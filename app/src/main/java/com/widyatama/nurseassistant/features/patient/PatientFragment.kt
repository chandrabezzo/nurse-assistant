package com.widyatama.nurseassistant.features.patient

import android.os.Bundle
import com.widyatama.nurseassistant.R
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.widyatama.core.base.BaseFragment
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.adapter.recyclerView.PatientRVAdapter
import com.widyatama.nurseassistant.constanta.AppConstans
import com.widyatama.nurseassistant.data.model.Patient
import com.widyatama.nurseassistant.features.detailPatient.DetailPatientActivity
import kotlinx.android.synthetic.main.fragment_patient.*
import org.koin.android.ext.android.inject

class PatientFragment : BaseFragment(), PatientViewContracts {

    val presenter: PatientPresenter<PatientViewContracts> by inject()

    val adapter: PatientRVAdapter by inject()
    val list = ArrayList<Patient>()
    var isError = false

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        showOptionMenu()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rv_patient.layoutManager = layoutManager
        rv_patient.adapter = adapter
        sr_patient.isRefreshing = true
        Handler().postDelayed({presenter.getAllPatient(20)}, 3000L)

        adapter.setOnItemClick(object : OnItemClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                var data = Bundle()
                data.putString(AppConstans.PATIENT, Gson().toJson(list[position]))
                launchActivity(DetailPatientActivity::class.java, data);
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }
        })

        sr_patient.setOnRefreshListener {
            isError = !isError
            Handler().postDelayed({presenter.getAllPatient(20)}, 5000L)
        }

        mb_refresh.setOnClickListener {
            isError = false

            sr_patient.visibility = View.VISIBLE
            sr_patient.isRefreshing = true

            Handler().postDelayed({
                presenter.getAllPatient(20)
            }, 3000L)
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_patient
    }

    override fun showPatient(values: ArrayList<Patient>) {
        sr_patient.isRefreshing = false

        if (isError){
            listError()
        }
        else {
            ll_error.visibility = View.GONE
            ll_list.visibility = View.VISIBLE

            list.clear()
            list.addAll(values)

            adapter.setItem(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_navigation, menu)
        val searchItem = menu?.findItem(R.id.nav_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.nama_lengkap)
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let { query ->
                    val filteredList = list.filter { it.nama.toLowerCase().contains(query.toLowerCase()) }
                    adapter.setItem(filteredList)
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })
    }

    override fun listError() {
        ll_error.visibility = View.VISIBLE
        sr_patient.visibility = View.GONE
        ll_list.visibility = View.GONE
    }
}
