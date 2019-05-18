package com.widyatama.nurseassistant.features.threatment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recyclerView.TimeLineRVAdapter
import com.widyatama.nurseassistant.data.model.OrderStatus
import com.widyatama.nurseassistant.data.model.TimeLineModel
import kotlinx.android.synthetic.main.activity_threatment.*

class ThreatmentActivity : BaseActivity(){

    lateinit var adapter : TimeLineRVAdapter
    val listTimeline = ArrayList<TimeLineModel>()
    lateinit var layoutManager : LinearLayoutManager

    override fun setLayout(): Int {
        return R.layout.activity_threatment
    }

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setDataListItems()
        initRecycle()
        btnKembali.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setDataListItems() {
        listTimeline.add(TimeLineModel("Pemeriksaan Glukosa", "", OrderStatus.INACTIVE))
        listTimeline.add(TimeLineModel("Cek Tekanan Darah", "2017-02-12 08:00", OrderStatus.ACTIVE))
        listTimeline.add(TimeLineModel("Ambil Darah", "2017-02-11 21:00", OrderStatus.COMPLETED))
    }

    private fun initRecycle() {
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = TimeLineRVAdapter(this, listTimeline)
    }


}
