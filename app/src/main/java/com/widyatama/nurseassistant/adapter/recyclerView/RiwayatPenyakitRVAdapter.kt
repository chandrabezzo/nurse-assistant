package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit
import kotlinx.android.synthetic.main.item_rv_riwayat_penyakit.view.*

class RiwayatPenyakitRVAdapter constructor(val context: Context, val list: ArrayList<RiwayatPenyakit>)
    : RecyclerView.Adapter<RiwayatPenyakitRVAdapter.Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_riwayat_penyakit,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: ArrayList<RiwayatPenyakit>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<RiwayatPenyakit>(itemView){
        override fun setContent(model: RiwayatPenyakit) {
            itemView.tv_nama.text = model.nama
            itemView.tv_tahun.text = model.tahun
        }
    }
}