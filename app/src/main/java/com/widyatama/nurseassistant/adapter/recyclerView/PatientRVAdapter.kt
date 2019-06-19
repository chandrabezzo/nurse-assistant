package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Patient
import kotlinx.android.synthetic.main.item_rv_patient.view.*

class PatientRVAdapter constructor(val context: Context, val list: ArrayList<Patient>)
    : RecyclerView.Adapter<PatientRVAdapter.Item>() {

    lateinit var listener: OnItemClickListener

    fun setOnItemClick(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_patient,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.setContent(list[position])
    }

    fun setItem(values: List<Patient>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Patient>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: Patient) {
            val tahun = context.getString(R.string.umur_tahun)
            itemView.tv_patient.text = "${model.nama} (${model.umur} $tahun)"
            itemView.tv_alamat.text = model.alamat
        }
    }
}