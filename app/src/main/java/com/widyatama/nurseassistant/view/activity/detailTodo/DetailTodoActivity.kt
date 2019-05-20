package com.widyatama.nurseassistant.view.activity.detailTodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recycleview.TodoRVdapter
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.features.main.MainActivity
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienActivity
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienFragment
import com.widyatama.nurseassistant.view.fragment.DoneFragment
import com.widyatama.nurseassistant.view.fragment.SheetCallback
import kotlinx.android.synthetic.main.activity_detail_todo.*
import org.koin.android.ext.android.inject

class DetailTodoActivity : BaseActivity(), DetailTodoViewContract, SheetCallback {

    val presenter : DetailTodoPresenter<DetailTodoViewContract> by inject()
    val rvTodo : TodoRVdapter by inject()
    var listTodo = ArrayList<String>()
    var doneSheetFragment = DoneFragment(this)
    var id : Int = 0

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        presenter.onAttach(this)
        id = intent.extras.getInt("id")
        initList()
        presenter?.getList(id)

        fabMenu.setOnClickListener {
            doneSheetFragment = DoneFragment(this)
            doneSheetFragment.show(supportFragmentManager, doneSheetFragment.tag)
        }

        btnKembali.setOnClickListener {
            onBackPressed()
        }

    }

    private fun initList(){
        var layoutManager = LinearLayoutManager(this)
        rv_list.layoutManager = layoutManager
        rv_list.adapter =rvTodo
    }

    override fun showResult(pasien: Pasien) {
        pasien.todoList?.let { listTodo.addAll(it) }
        name.text = pasien.name
        floor.text = pasien.floor + " " + pasien.room + " " + pasien.bed
        println("=========== size "+ listTodo.size)
        rvTodo.setItem(listTodo)
        rvTodo.notifyDataSetChanged()
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_todo
    }

    override fun onFinish() {
        doneSheetFragment.dismiss()
        presenter?.delete(id)
        launchActivity<MainActivity>(true) {
        }    }
}
