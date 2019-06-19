package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Nurse
import kotlinx.android.synthetic.main.item_rv_other_nurse.view.*

class OtherNurseRVAdapter constructor(val context: Context, val list: ArrayList<Nurse>)
    : RecyclerView.Adapter<OtherNurseRVAdapter.Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_other_nurse,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.setContent(list[position])
    }

    fun setItem(values: List<Nurse>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Nurse>(itemView){
        override fun setContent(model: Nurse) {
            itemView.tv_nama.text = model.nama
            itemView.tv_info.text = "${model.nip} - ${model.phoneNumber}"
        }
    }
}