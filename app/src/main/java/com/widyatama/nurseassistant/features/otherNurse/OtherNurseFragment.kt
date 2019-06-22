package com.widyatama.nurseassistant.features.otherNurse


import android.os.Bundle
import com.widyatama.nurseassistant.R
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.adapter.recyclerView.OtherNurseRVAdapter
import com.widyatama.nurseassistant.data.model.Nurse
import kotlinx.android.synthetic.main.fragment_other_nurse.*
import org.koin.android.ext.android.inject

class OtherNurseFragment : BaseFragment(), OtherNurseViewContracts {

    val presenter: OtherNursePresenter<OtherNurseViewContracts> by inject()

    val adapter: OtherNurseRVAdapter by inject()
    val list = ArrayList<Nurse>()
    var isError = false

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        showOptionMenu()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rv_nurse.layoutManager = layoutManager
        rv_nurse.adapter = adapter
        sr_nurse.isRefreshing = true
        Handler().postDelayed({presenter.getAllNurse()}, 3000L)

        sr_nurse.setOnRefreshListener {
            isError = !isError
            Handler().postDelayed({presenter.getAllNurse()}, 3000L)
        }

        mb_refresh.setOnClickListener {
            isError = false

            sr_nurse.visibility = View.VISIBLE
            sr_nurse.isRefreshing = true

            Handler().postDelayed({
                presenter.getAllNurse()
            }, 3000L)
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_other_nurse
    }

    override fun showNurse(values: List<Nurse>) {
        sr_nurse.isRefreshing = false

        if (isError) {
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
                    val filteredList = list.filter { it.nama.toLowerCase()
                            .contains(query.toLowerCase()) }
                    adapter.setItem(filteredList)
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })
    }

    override fun listError() {
        ll_error.visibility = View.VISIBLE
        sr_nurse.visibility = View.GONE
        ll_list.visibility = View.GONE
    }
}
