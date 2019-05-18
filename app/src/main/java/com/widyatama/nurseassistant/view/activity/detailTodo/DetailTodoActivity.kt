package com.widyatama.nurseassistant.view.activity.detailTodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recycleview.TodoRVdapter
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienActivity
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienFragment
import com.widyatama.nurseassistant.view.fragment.DoneFragment
import com.widyatama.nurseassistant.view.fragment.SheetCallback
import kotlinx.android.synthetic.main.activity_detail_todo.*
import org.koin.android.ext.android.inject

class DetailTodoActivity : BaseActivity(), DetailTodoViewContract, SheetCallback {

    val presenter : DetailTodoPresenter<DetailTodoViewContract> by inject()
    lateinit var rvTodo : TodoRVdapter
    var listTodo = ArrayList<String>()
    var layoutManager = LinearLayoutManager(this)
    var doneSheetFragment = DoneFragment(this)

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        presenter.onAttach(this)
//        val id = intent.extras.getInt("id")
        initList()
        presenter?.getList(2)

        fabMenu.setOnClickListener {
            doneSheetFragment = DoneFragment(this)
            doneSheetFragment.show(supportFragmentManager, doneSheetFragment.tag)
        }

    }

    private fun initList(){
        rvTodo = TodoRVdapter(this, listTodo)
        rv_list.layoutManager = layoutManager
        rv_list.adapter =rvTodo
    }

    override fun showResult(pasien: Pasien) {
        listTodo.clear()
        pasien.todoList?.let { listTodo.addAll(it) }
        name.text = pasien.name
        floor.text = pasien.floor + " " + pasien.room + " " + pasien.bed
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_todo
    }

    override fun onFinish() {
        println("=============== oke")
        doneSheetFragment.dismiss()
        presenter?.delete(2)
        launchActivity<ListPasienActivity>(true) {
        }    }
}
