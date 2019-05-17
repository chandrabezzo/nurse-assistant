package com.widyatama.nurseassistant.features.healingPlan


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recyclerView.HealingRVAdapter
import com.widyatama.nurseassistant.data.model.HealingPlan
import kotlinx.android.synthetic.main.fragment_healing_plan.*
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class HealingPlanFragment : BaseFragment(), HealingPlanViewContracts {

    val presenter: HealingPlanPresenter<HealingPlanViewContracts> by inject()
    val adapter: HealingRVAdapter by inject()
    val list = ArrayList<HealingPlan>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rv_healing.layoutManager = layoutManager
        rv_healing.adapter = adapter
        presenter.getHealing(20)

        val cal = Calendar.getInstance()
        tv_bulan.text = "Mei"
        tv_tanggal.text = cal.get(Calendar.DAY_OF_MONTH).toString()
        tv_greeting.text = getString(R.string.selamat_pagi)
        tv_user.text = "Yang Zhen, A.Md. Kep."
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_healing_plan
    }

    override fun showHealing(values: ArrayList<HealingPlan>) {
        list.clear()
        list.addAll(values)
        adapter.setItem(list)
        adapter.notifyDataSetChanged()
    }
}
