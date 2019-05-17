package com.widyatama.nurseassistant.view.activity.listPasien

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.getbase.floatingactionbutton.FloatingActionButton
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recycleview.PasienRVAdapter
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.view.activity.detailTodo.DetailTodoActivity
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienFragment
import com.widyatama.nurseassistant.view.fragment.SheetCallback
import kotlinx.android.synthetic.main.activity_list_pasien.*
import org.koin.android.ext.android.inject

class ListPasienActivity : BaseActivity(), ListPasienViewContract, SheetCallback{

    val presenter : ListPasienPresenter<ListPasienViewContract> by inject()
    lateinit var rvPasient : PasienRVAdapter
    var listPasien = ArrayList<Pasien>()
    var layoutManager = LinearLayoutManager(this)
    var bottomSheetFragment = BottomAddPasienFragment(this)

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        supportActionBar?.title = "Visit Pasien"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initFab()
        initList()

        sr_list.setOnRefreshListener {
            presenter?.getList()
        }
    }

    private fun initList(){
        rvPasient = PasienRVAdapter(this, listPasien)
        rv_list.layoutManager = layoutManager
        rv_list.adapter =rvPasient

        presenter?.getList()

        rvPasient.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return false
            }

            override fun onItemClick(itemView: View, position: Int) {
                println("=============== id")
                val item = listPasien.get(position)
                launchActivity<DetailTodoActivity>(true) {
                    putExtra("id", item.id)
                }
            }
        })

    }

    private fun initFab() {
        val fabAction = FloatingActionButton(this)
        fabAction.title = "Tambah Pasien"
        fabAction.setIconDrawable(resources.getDrawable(R.drawable.ic_person_add_black_24dp))
        fabAction.colorNormal = resources.getColor(R.color.blueLight)
        fabAction.setOnClickListener {
            showBottomSheetDialogFragment()
        }
        fabMenu.addButton(fabAction)
    }

    private fun showBottomSheetDialogFragment() {
        bottomSheetFragment = BottomAddPasienFragment(this)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    override fun setLayout(): Int {
        return R.layout.activity_list_pasien
    }

    override fun onFinish() {
        bottomSheetFragment.dismiss()
        presenter?.getList()
        fabMenu.collapse()
    }

    override fun showPasien(pasien: List<Pasien>) {
        sr_list.isRefreshing = false
        listPasien.clear()
        listPasien.addAll(pasien)
    }

    override fun showLoading() {
        pb_load_more.visibility = View.VISIBLE
        rv_list.visibility = View.GONE
    }

    override fun hideLoading() {
        pb_load_more.visibility = View.GONE
        rv_list.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
