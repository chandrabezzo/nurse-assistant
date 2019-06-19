package com.widyatama.nurseassistant.features.jadwal


import android.os.Bundle
import com.widyatama.nurseassistant.R
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.adapter.CalendarAdapter
import com.widyatama.nurseassistant.adapter.recyclerView.JadwalRVAdapter
import com.widyatama.nurseassistant.customView.FlexibleCalendar
import com.widyatama.nurseassistant.data.model.Day
import com.widyatama.nurseassistant.data.model.Jadwal
import kotlinx.android.synthetic.main.fragment_jadwal.*
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class JadwalFragment : BaseFragment(), JadwalViewContracts {

    val presenter: JadwalPresenter<JadwalViewContracts> by inject()
    val adapter: JadwalRVAdapter by inject()

    val list = ArrayList<Jadwal>()
    var cal = Calendar.getInstance()
    val day = Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    lateinit var calendarAdapter : CalendarAdapter

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rv_jadwal.layoutManager = layoutManager
        rv_jadwal.adapter = adapter

        initCalendar(cal)

        cv_jadwal.setCalendarListener(object : FlexibleCalendar.CalendarListener {
            override fun onDaySelect() {
                val selected = cv_jadwal.selectedDay
                val filtered = list.filter { it.tanggal == selected.day }
                adapter.setItem(filtered)
                adapter.notifyDataSetChanged()
            }

            override fun onItemClick(v: View?) {

            }

            override fun onDataUpdate() {

            }

            override fun onMonthChange() {

            }

            override fun onWeekChange(position: Int) {

            }
        })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_jadwal
    }

    override fun initCalendar(cal: Calendar) {
        cv_jadwal.selectedItem = day
        calendarAdapter = CalendarAdapter(context, cal)
        cv_jadwal.setAdapter(calendarAdapter)
        presenter.getJadwal(20)

        iv_more_calendar.setOnClickListener {
            if (cv_jadwal.state == FlexibleCalendar.STATE_COLLAPSED) {
                cv_jadwal.expand(500)
                iv_more_calendar.setImageResource(R.drawable.ic_expand_less_primary_24dp)
            } else if (cv_jadwal.state == FlexibleCalendar.STATE_EXPANDED) {
                cv_jadwal.collapse(500)
                iv_more_calendar.setImageResource(R.drawable.ic_expand_more_primary_24dp)
            }
        }
    }

    override fun showJadwal(values: ArrayList<Jadwal>) {
        list.clear()
        list.addAll(values)
        adapter.setItem(list)
        adapter.notifyDataSetChanged()

        for (value in values){
            cv_jadwal.addEventTag(value.year, value.month - 1, value.tanggal)
        }
    }
}
