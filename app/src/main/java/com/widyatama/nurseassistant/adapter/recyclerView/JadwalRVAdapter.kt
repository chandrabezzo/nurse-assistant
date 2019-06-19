package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Jadwal
import kotlinx.android.synthetic.main.item_rv_jadwal.view.*

class JadwalRVAdapter constructor(val context: Context, val list: ArrayList<Jadwal>)
    : RecyclerView.Adapter<JadwalRVAdapter.Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_jadwal,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.setContent(list[position])
    }

    fun setItem(values: List<Jadwal>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Jadwal>(itemView){
        override fun setContent(model: Jadwal) {
            itemView.tv_jam.text = "${model.jamMulai} - ${model.jamSelesai}"
            itemView.tv_ruangan.text = model.ruangan
        }
    }
}