package com.widyatama.nurseassistant.features.threatment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.widyatama.core.base.BaseActivity;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.adapter.recyclerView.TimeLineRVAdapter;
import com.widyatama.nurseassistant.data.model.OrderStatus;
import com.widyatama.nurseassistant.data.model.TimeLineModel;

import java.util.ArrayList;

public class ThreatmentActivity extends BaseActivity {
    private TimeLineRVAdapter adapter;
    private ArrayList<TimeLineModel> listTimeLine = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Override
    protected Integer setLayout() {
        return R.layout.activity_threatment;
    }

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        setDataListItems();
        initRecycle();
        AppCompatImageView btnKembali = this.findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setDataListItems() {
        listTimeLine.add(new TimeLineModel("Pemeriksaan Glukosa", "", OrderStatus.INACTIVE));
        listTimeLine.add(new TimeLineModel("Cek Tekanan Darah", "2017-02-12 08:00", OrderStatus.ACTIVE));
        listTimeLine.add(new TimeLineModel("Ambil Darah", "2017-02-11 21:00", OrderStatus.COMPLETED));
    }

    private void initRecycle() {
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(new TimeLineRVAdapter(this, listTimeLine));
    }
}
