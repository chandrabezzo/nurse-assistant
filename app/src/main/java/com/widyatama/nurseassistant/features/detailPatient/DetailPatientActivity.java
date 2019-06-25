package com.widyatama.nurseassistant.features.detailPatient;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.widyatama.core.base.BaseActivity;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.adapter.recyclerView.RiwayatPenyakitRVAdapter;
import com.widyatama.nurseassistant.constanta.AppConstans;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;
import com.widyatama.nurseassistant.data.model.Patient;
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class DetailPatientActivity extends BaseActivity implements DetailPatientViewContracts {
    DetailPatientPresenter<DetailPatientViewContracts> presenter;

    RiwayatPenyakitRVAdapter adapter;
    ArrayList<RiwayatPenyakit> list = new ArrayList<>();

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        presenter = new DetailPatientPresenter<DetailPatientViewContracts>(new LocalStorageHelper(this),
                new SchedulerProviderUtil(), new CompositeDisposable());

        presenter.onAttach(this);

        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        displayHome();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationClick();
            }
        });

        setActionBarTitle(getString(R.string.detail_patient));

        presenter.getInformation(dataReceived.getString(AppConstans.PATIENT));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RiwayatPenyakitRVAdapter(getContext(), list);
        RecyclerView rvRiwayatPenyakit = ((RecyclerView) findViewById(R.id.rv_riwayat_penyakit));
        rvRiwayatPenyakit.setLayoutManager(layoutManager);
        rvRiwayatPenyakit.setAdapter(adapter);
        presenter.getRiwayatPenyakit(20);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_detail_patient;
    }

    @Override
    public void showInformation(Patient value) {
        AppCompatTextView tvNama = findViewById(R.id.tv_nama);
        AppCompatTextView tvAge = findViewById(R.id.tv_age);
        AppCompatTextView tvPhone = findViewById(R.id.tv_phone);
        AppCompatTextView tvAlamat = findViewById(R.id.tv_alamat);

        tvNama.setText(value.getNama());
        tvAge.setText(value.getUmur() + " " + getString(R.string.umur_tahun));
        tvPhone.setText(value.getPhoneNumber());
        tvAlamat.setText(value.getAlamat());
    }

    @Override
    public void showRiwayat(List<RiwayatPenyakit> values) {
        list.clear();
        list.addAll(values);
        adapter.setItem(list);
        adapter.notifyDataSetChanged();
    }
}
