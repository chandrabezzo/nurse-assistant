package com.widyatama.nurseassistant.features.jadwal;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.widyatama.core.base.BaseFragment;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.adapter.CalendarAdapter;
import com.widyatama.nurseassistant.adapter.recyclerView.JadwalRVAdapter;
import com.widyatama.nurseassistant.customView.FlexibleCalendar;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;
import com.widyatama.nurseassistant.data.model.Day;
import com.widyatama.nurseassistant.data.model.Jadwal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class JadwalFragment extends BaseFragment implements JadwalViewContracts {

    private JadwalPresenter<JadwalViewContracts> presenter;
    private JadwalRVAdapter adapter;

    ArrayList<Jadwal> list = new ArrayList<>();
    Calendar cal = Calendar.getInstance();
    Day day = new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    CalendarAdapter calendarAdapter;
    FlexibleCalendar cvJadwal;

    @Override
    protected void onViewInitialized(Bundle savedInstanceState) {
        presenter = new JadwalPresenter<JadwalViewContracts>(new LocalStorageHelper(getContext()),
            new SchedulerProviderUtil(), new CompositeDisposable());

        presenter.onAttach(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cvJadwal = getActivity().findViewById(R.id.cv_jadwal);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView rvJadwal = getActivity().findViewById(R.id.rv_jadwal);
        rvJadwal.setLayoutManager(layoutManager);
        rvJadwal.setAdapter(adapter);

        initCalendar(cal);

        cvJadwal.setCalendarListener(new FlexibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day selected = cvJadwal.getSelectedDay();
                List<Jadwal> filtered = new ArrayList<>();
                for (int counter = 0; counter <= list.size(); counter++){
                    if (list.get(counter).getTanggal() == selected.getDay()){
                        filtered.add(list.get(counter));
                    }
                }
                adapter.setItem(filtered);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemClick(View v) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int position) {

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
        return R.layout.fragment_jadwal;
    }

    @Override
    public void initCalendar(Calendar cal) {
        cvJadwal.setSelectedItem(day);
        calendarAdapter = new CalendarAdapter(getContext(), cal);
        cvJadwal.setAdapter(calendarAdapter);
        presenter.getJadwal(20);

        AppCompatImageView ivMoreCalendar = getActivity().findViewById(R.id.iv_more_calendar);
        ivMoreCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cvJadwal.getState() == FlexibleCalendar.STATE_COLLAPSED) {
                    cvJadwal.expand(500);
                    ivMoreCalendar.setImageResource(R.drawable.ic_expand_less_primary_24dp);
                } else if (cvJadwal.getState() == FlexibleCalendar.STATE_EXPANDED) {
                    cvJadwal.collapse(500);
                    ivMoreCalendar.setImageResource(R.drawable.ic_expand_more_primary_24dp);
                }
            }
        });
    }

    @Override
    public void showJadwal(List<Jadwal> values) {
        list.clear();
        list.addAll(values);
        adapter.setItem(list);
        adapter.notifyDataSetChanged();

        for (int counter = 0; counter <= values.size(); counter++){
            cvJadwal.addEventTag(values.get(counter).getYear(), values.get(counter).getMonth() - 1,
                    values.get(counter).getTanggal());
        }
    }
}
