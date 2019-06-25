package com.widyatama.nurseassistant.view.activity.listPasien;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.widyatama.core.base.BaseActivity;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.adapter.recyclerView.ListPasienAdapter;
import com.widyatama.nurseassistant.data.model.Pasien;
import com.widyatama.nurseassistant.view.activity.detailTodo.DetailTodoActivity;
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienFragment;
import com.widyatama.nurseassistant.view.fragment.SheetCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListPasienActivity extends BaseActivity implements ListPasienViewContract, SheetCallback {

    private ListPasienPresenter presenter;
    private BottomAddPasienFragment bottomAddPasienFragment;
    private BottomSheetDialogFragment bottomSheetDialogFragment;
    private ListPasienAdapter adapter;
    private List<Pasien> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView notif;
    private FloatingActionsMenu fabMenu;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        presenter = new ListPasienPresenter(getContext(), this);
        bottomAddPasienFragment = new BottomAddPasienFragment(this);
        initRecycleview();
        initFabView();
        presenter.getList();
    }

    private void initRecycleview(){
        notif = (TextView) findViewById(R.id.notif);
        recyclerView = (RecyclerView) findViewById(R.id.rv_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListPasienAdapter(list, getContext());
        recyclerView.setAdapter(adapter);

        adapter.setOnClick(new ListPasienAdapter.OnItemClick() {
            @Override
            public void onItemClick(Pasien pasien) {
                Bundle data = new Bundle();
                data.putInt("id", pasien.getId());
                Intent intent = new Intent(ListPasienActivity.this, DetailTodoActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });

    }

    private void initFabView(){
        fabMenu = findViewById(R.id.fabMenu);
        FloatingActionButton fabButton = new FloatingActionButton(this);
        fabButton.setTitle("Tambah Pasien");
        fabButton.setIconDrawable(getResources().getDrawable(R.drawable.ic_person_add_black_24dp));
        fabButton.setColorNormal(getResources().getColor(R.color.blueLight));
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });
        fabMenu.addButton(fabButton);
        FloatingActionButton fabButtonExport = new FloatingActionButton(this);
        fabButtonExport.setTitle("Export File");
        fabButtonExport.setIconDrawable(getResources().getDrawable(R.drawable.ic_event_note_white_24dp));
        fabButtonExport.setColorNormal(getResources().getColor(R.color.orange));
        fabButtonExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
        fabMenu.addButton(fabButtonExport);
    }

    private void showBottomDialog(){
        bottomSheetDialogFragment = new BottomAddPasienFragment(this);
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_list_pasien;
    }

    @Override
    public void showPasien(@NotNull List<Pasien> pasien) {
        list.addAll(pasien);
        adapter.setItem(pasien);
        adapter.notifyDataSetChanged();
        if (pasien.size() > 0)
            notif.setVisibility(View.GONE);
    }

    @Override
    public void onDetachView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFinish() {
        bottomSheetDialogFragment.dismiss();
        presenter.getList();
        fabMenu.collapse();
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat
                    .requestPermissions(ListPasienActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            presenter.exportToExcel(list);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    presenter.exportToExcel(list);
                } else {
                    // Permission Denied
                    Toast.makeText(ListPasienActivity.this, "Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
