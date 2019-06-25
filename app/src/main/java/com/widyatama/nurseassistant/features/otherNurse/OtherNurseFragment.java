package com.widyatama.nurseassistant.features.otherNurse;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.widyatama.core.base.BaseFragment;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.adapter.recyclerView.OtherNurseRVAdapter;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;
import com.widyatama.nurseassistant.data.model.Nurse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class OtherNurseFragment extends BaseFragment implements OtherNurseViewContracts {
    private OtherNursePresenter<OtherNurseViewContracts> presenter;

    private OtherNurseRVAdapter adapter;
    private ArrayList<Nurse> list = new ArrayList<>();
    private boolean isError = false;
    SwipeRefreshLayout srNurse;
    LinearLayout llError;
    LinearLayout llList;

    @Override
    protected void onViewInitialized(Bundle savedInstanceState) {
        presenter = new OtherNursePresenter<>(new LocalStorageHelper(getContext()), new SchedulerProviderUtil(),
                new CompositeDisposable());
        presenter.onAttach(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new OtherNurseRVAdapter(getContext(), list);
        RecyclerView rvNurse = getActivity().findViewById(R.id.rv_nurse);
        llError = getActivity().findViewById(R.id.ll_error);
        llList = getActivity().findViewById(R.id.ll_list);
        rvNurse.setLayoutManager(layoutManager);
        rvNurse.setAdapter(adapter);
        srNurse = getActivity().findViewById(R.id.sr_nurse);
        srNurse.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getAllNurse();
            }
        }, 3000);

        srNurse.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isError = !isError;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.getAllNurse();
                    }
                }, 3000);
            }
        });

        MaterialButton mbRefresh = getActivity().findViewById(R.id.mb_refresh);
        mbRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isError = false;

                srNurse.setVisibility(View.VISIBLE);
                srNurse.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.getAllNurse();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected Integer setLayout() {
        return R.layout.fragment_other_nurse;
    }

    @Override
    public void showNurse(List<Nurse> values) {
        srNurse.setRefreshing(false);

        if (isError) {
            listError();
        }
        else {
            llError.setVisibility(View.GONE);
            llList.setVisibility(View.VISIBLE);

            list.clear();
            list.addAll(values);

            adapter.setItem(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_navigation, menu);
        MenuItem searchItem = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.nama_lengkap));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Nurse> filteredList = new ArrayList<>();
                for(int counter = 0; counter <= list.size(); counter++){
                    if(list.get(counter).getNama().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(list.get(counter));
                    }
                }
                adapter.setItem(filteredList);
                adapter.notifyDataSetChanged();

                return true;
            }
        });
    }

    @Override
    public void listError() {
        llError.setVisibility(View.VISIBLE);
        srNurse.setVisibility(View.GONE);
        llList.setVisibility(View.GONE);
    }
}
