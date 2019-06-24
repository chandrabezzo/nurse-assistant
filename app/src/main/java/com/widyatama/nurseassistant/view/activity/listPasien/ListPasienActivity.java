package com.widyatama.nurseassistant.view.activity.listPasien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    private FloatingActionsMenu fabMenu;

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        presenter = new ListPasienPresenter(getContext(), this);
        bottomAddPasienFragment = new BottomAddPasienFragment(this);
        initRecycleview();
        initFabView();
        presenter.getList();
    }

    private void initRecycleview(){
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
        adapter.setItem(pasien);
        adapter.notifyDataSetChanged();
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
    }
}
