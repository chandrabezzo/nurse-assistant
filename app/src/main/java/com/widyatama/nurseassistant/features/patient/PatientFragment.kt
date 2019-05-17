package com.widyatama.nurseassistant.features.patient

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.widyatama.core.base.BaseFragment
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
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

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        showOptionMenu()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rv_patient.layoutManager = layoutManager
        rv_patient.adapter = adapter
        presenter.getAllPatient(20)

        adapter.setOnItemClick(object : OnItemClickListener{
            override fun onItemClick(itemView: View, position: Int) {
                launchActivity<DetailPatientActivity>{
                    putExtra(AppConstans.PATIENT, Gson().toJson(list[position]))
                }
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }
        })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_patient
    }

    override fun showPatient(values: ArrayList<Patient>) {
        list.clear()
        list.addAll(values)

        adapter.setItem(list)
        adapter.notifyDataSetChanged()
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
                    val filteredList = list.filter { it.nama.contains(query) }
                    adapter.setItem(filteredList)
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })
    }
}
