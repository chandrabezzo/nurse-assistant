package com.widyatama.nurseassistant.features.healingPlan

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.getbase.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.widyatama.core.base.BaseFragment
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recyclerView.HealingRVAdapter
import com.widyatama.nurseassistant.constanta.AppConstans
import com.widyatama.nurseassistant.data.model.HealingPlan
import com.widyatama.nurseassistant.features.threatment.ThreatmentActivity
import com.widyatama.nurseassistant.view.activity.EventActivity
import com.widyatama.nurseassistant.view.activity.detailTodo.DetailTodoActivity
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienActivity
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
        initFab()
        val layoutManager = LinearLayoutManager(context)
        rv_healing.layoutManager = layoutManager
        rv_healing.adapter = adapter
        presenter.getHealing(20)

        val cal = Calendar.getInstance()
        tv_bulan.text = "Mei"
        tv_tanggal.text = cal.get(Calendar.DAY_OF_MONTH).toString()
        tv_greeting.text = getString(R.string.selamat_pagi)
        tv_user.text = "Yang Zhen, A.Md. Kep."

        adapter.setOnItemClick(object : OnItemClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                launchActivity<ListPasienActivity>{
                }
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }
        })

    }

    private fun initFab() {
        val fabAction = FloatingActionButton(context)
        fabAction.title = "Visit Pasien"
        fabAction.setIconDrawable(resources.getDrawable(R.drawable.ic_person_white))
        fabAction.colorNormal = resources.getColor(R.color.orange)
        fabAction.setOnClickListener {
            launchActivity<ListPasienActivity>{
            }
        }
        fabMenu.addButton(fabAction)
        val fabActionThreat = FloatingActionButton(context)
        fabActionThreat.title = "Event"
        fabActionThreat.setIconDrawable(resources.getDrawable(R.drawable.ic_event_note_white_24dp))
        fabActionThreat.colorNormal = resources.getColor(R.color.greenTransparent)
        fabActionThreat.setOnClickListener {
            launchActivity<EventActivity>{
            }
        }
        fabMenu.addButton(fabActionThreat)
        val fabActionEvent = FloatingActionButton(context)
        fabActionEvent.title = "Threatment"
        fabActionEvent.setIconDrawable(resources.getDrawable(R.drawable.ic_accessible_white_24dp))
        fabActionEvent.colorNormal = resources.getColor(R.color.yellow)
        fabActionEvent.setOnClickListener {
            launchActivity<ThreatmentActivity>{
            }
        }
        fabMenu.addButton(fabActionEvent)

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
