package com.widyatama.nurseassistant.features.jadwal;

import com.widyatama.core.base.BaseFragmentContract;
import com.widyatama.nurseassistant.data.model.Jadwal;

import java.util.Calendar;
import java.util.List;

public interface JadwalViewContracts extends BaseFragmentContract {
    void initCalendar(Calendar cal);

    void showJadwal(List<Jadwal> values);
}
