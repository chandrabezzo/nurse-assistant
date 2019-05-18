package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.HealingPlan
import kotlinx.android.synthetic.main.item_rv_healing.view.*

class HealingRVAdapter constructor(val context: Context, val list: ArrayList<HealingPlan>)
    : RecyclerView.Adapter<HealingRVAdapter.Item>() {

    lateinit var listener: OnItemClickListener

    fun setOnItemClick(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_healing,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: List<HealingPlan>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<HealingPlan>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: HealingPlan) {
            itemView.tv_judul_healing.text = model.tindakan
            itemView.tv_hasil_healing.text = model.deskripsi
            itemView.iv_jenis.setImageResource(model.jenis)
        }
    }
}