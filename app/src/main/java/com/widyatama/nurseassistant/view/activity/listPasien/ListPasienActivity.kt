package com.widyatama.nurseassistant.view.activity.listPasien

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.getbase.floatingactionbutton.FloatingActionButton
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recyclerView.TodoPasienRVAdapter
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.view.activity.detailTodo.DetailTodoActivity
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienFragment
import com.widyatama.nurseassistant.view.fragment.SheetCallback
import kotlinx.android.synthetic.main.activity_list_pasien.*
import org.koin.android.ext.android.inject

class ListPasienActivity : BaseActivity(), ListPasienViewContract, SheetCallback{

    val presenter : ListPasienPresenter<ListPasienViewContract> by inject()
    val adapter : TodoPasienRVAdapter by inject()
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

        println("==============1")
        adapter.setOnItemClick(object  : TodoPasienRVAdapter.OnItemClickListeners{
            override fun onItemClick(item: Pasien) {
                launchActivity<DetailTodoActivity>{
                    putExtra("id", item.id)
                }
            }
        })


        presenter?.getList()
    }

    private fun initList(){
        val layoutManager = LinearLayoutManager(this)
        rv_todo.layoutManager = layoutManager
        rv_todo.adapter = adapter

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
        adapter.setItem(pasien)
        adapter.notifyDataSetChanged()
        if (pasien.size > 0)
            notif.visibility = View.GONE
    }

    override fun showLoading() {
        pb_load_more.visibility = View.VISIBLE
        rv_todo.visibility = View.GONE
        notif.visibility = View.GONE
    }

    override fun hideLoading() {
        pb_load_more.visibility = View.GONE
        rv_todo.visibility = View.VISIBLE
        notif.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
