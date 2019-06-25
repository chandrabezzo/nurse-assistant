package com.widyatama.nurseassistant.view.activity.detailTodo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.widyatama.core.base.BaseActivity;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.adapter.recyclerView.ListDetailPasienAdapter;
import com.widyatama.nurseassistant.data.model.Pasien;
import com.widyatama.nurseassistant.features.main.MainActivity;
import com.widyatama.nurseassistant.view.fragment.DoneFragment;
import com.widyatama.nurseassistant.view.fragment.SheetCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DetailTodoActivity extends BaseActivity implements DetailTodoViewContract, SheetCallback {

    private DetailTodoPresenter presenter;
    private ListDetailPasienAdapter adapter;
    private List<String> todoList = new ArrayList<>();
    private DoneFragment doneFragment;

    @Override
    protected void onInitializedView(Bundle savedInstanceState) {
        presenter = new DetailTodoPresenter(getContext(), this);
        initComponent();
        initRecycView();

    }

    private void initComponent() {
        int data = dataReceived.getInt("id");
        presenter.getList(data);

        FloatingActionButton fabMenu = (FloatingActionButton) findViewById(R.id.fabMenu);
        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneFragment = new DoneFragment(DetailTodoActivity.this);
                doneFragment.show(getSupportFragmentManager(), doneFragment.getTag());
            }
        });

        ImageView btnKembali = (ImageView) findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRecycView() {
        adapter = new ListDetailPasienAdapter(todoList, getContext());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Integer setLayout() {
        return R.layout.activity_detail_todo;
    }

    @Override
    public void showResult(@NotNull Pasien pasien) {
        todoList.addAll(pasien.getTodoList());
        adapter.setItem(todoList);
        adapter.notifyDataSetChanged();

        TextView name = (TextView) findViewById(R.id.name);
        TextView floor = (TextView) findViewById(R.id.floor);

        name.setText(pasien.getName());
        floor.setText(pasien.getFloor() + " " + pasien.getRoom() + " " + pasien.getBed());

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
        doneFragment.dismiss();
        presenter.delete(dataReceived.getInt("id"));
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
