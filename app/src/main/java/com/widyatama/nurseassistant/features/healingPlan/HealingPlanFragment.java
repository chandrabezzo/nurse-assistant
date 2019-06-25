package com.widyatama.nurseassistant.features.healingPlan;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.widyatama.core.base.BaseFragment;
import com.widyatama.core.listener.OnItemClickListener;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.adapter.recyclerView.HealingRVAdapter;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;
import com.widyatama.nurseassistant.data.model.HealingPlan;
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class HealingPlanFragment extends BaseFragment implements HealingPlanViewContracts {

    HealingPlanPresenter<HealingPlanViewContracts> presenter;
    HealingRVAdapter adapter;
    ArrayList<HealingPlan> list = new ArrayList<>();

    @Override
    protected void onViewInitialized(Bundle savedInstanceState) {
        presenter = new HealingPlanPresenter<>(new LocalStorageHelper(getContext()),
                new SchedulerProviderUtil(), new CompositeDisposable());
        presenter.onAttach(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFab();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView rvHealing = getActivity().findViewById(R.id.rv_healing);
        adapter = new HealingRVAdapter(getContext(), list);
        rvHealing.setLayoutManager(layoutManager);
        rvHealing.setAdapter(adapter);
        presenter.getHealing(20);

        Calendar cal = Calendar.getInstance();
        AppCompatTextView tvBulan = getActivity().findViewById(R.id.tv_bulan);
        AppCompatTextView tvTanggal = getActivity().findViewById(R.id.tv_bulan);
        AppCompatTextView tvGreeting = getActivity().findViewById(R.id.tv_bulan);
        AppCompatTextView tvUser = getActivity().findViewById(R.id.tv_bulan);

        tvBulan.setText("Mei");
        tvTanggal.setText(cal.get(Calendar.DAY_OF_MONTH));
        tvGreeting.setText(getString(R.string.selamat_pagi));
        tvUser.setText("Yang Zhen, A.Md. Kep.");

        adapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, Integer position) {
                launchActivity(ListPasienActivity.class);
            }

            @Override
            public boolean onItemLongClick(View itemView, Integer position) {
                return true;
            }
        });
    }

    private void initFab(){
        FloatingActionButton fabAction = new FloatingActionButton(getContext());
        fabAction.setTitle("Visit Pasien");
        fabAction.setIconDrawable(getResources().getDrawable(R.drawable.ic_person_white));
        fabAction.setColorNormal(getResources().getColor(R.color.orange));
        fabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(ListPasienActivity.class);
            }
        });

        FloatingActionsMenu fabMenu = new FloatingActionsMenu(getContext());
        fabMenu.addButton(fabAction);
        FloatingActionButton fabActionThreat = new FloatingActionButton(getContext());
        fabActionThreat.setTitle("Event");
        fabActionThreat.setIconDrawable(getResources().getDrawable(R.drawable.ic_event_note_white_24dp));
        fabActionThreat.setColorNormal(getResources().getColor(R.color.greenTransparent));
        fabActionThreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(ListPasienActivity.class);
            }
        });
        fabMenu.addButton(fabActionThreat);

        FloatingActionButton fabActionEvent = new FloatingActionButton(getContext());
        fabActionEvent.setTitle("Threatment");
        fabActionEvent.setIconDrawable(getResources().getDrawable(R.drawable.ic_accessible_white_24dp));
        fabActionEvent.setColorNormal(getResources().getColor(R.color.yellow));
        fabActionEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(ListPasienActivity.class);
            }
        });

        fabMenu.addButton(fabActionEvent);
    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected Integer setLayout() {
        return R.layout.fragment_healing_plan;
    }

    @Override
    public void showHealing(List<HealingPlan> values) {
        list.clear();
        list.addAll(values);
        adapter.setItem(list);
        adapter.notifyDataSetChanged();
    }
}
