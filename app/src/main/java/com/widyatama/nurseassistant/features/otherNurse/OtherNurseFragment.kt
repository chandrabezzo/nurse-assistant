package com.widyatama.nurseassistant.features.otherNurse


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recyclerView.OtherNurseRVAdapter
import com.widyatama.nurseassistant.data.model.Nurse
import kotlinx.android.synthetic.main.fragment_other_nurse.*
import org.koin.android.ext.android.inject

class OtherNurseFragment : BaseFragment(), OtherNurseViewContracts {

    val presenter: OtherNursePresenter<OtherNurseViewContracts> by inject()
    val adapter: OtherNurseRVAdapter by inject()
    val list = ArrayList<Nurse>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        showOptionMenu()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rv_nurse.layoutManager = layoutManager
        rv_nurse.adapter = adapter
        presenter.getAllNurse()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_other_nurse
    }

    override fun showNurse(values: ArrayList<Nurse>) {
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
                searchView.clearFocus()
                query?.let {  }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}
