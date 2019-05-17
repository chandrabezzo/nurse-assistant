package com.widyatama.nurseassistant.features.jadwal

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

import com.widyatama.nurseassistant.data.model.Jadwal
import java.util.*

interface JadwalViewContracts: BaseFragmentContract {
    fun initCalendar(cal: Calendar)

    fun showJadwal(values: ArrayList<Jadwal>)
}

interface JadwalPresenterContracts<V: JadwalViewContracts>: BasePresenterContract<V> {
    fun getJadwal(limit: Int)
}