package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.recycleview.TodoRVdapter
import com.widyatama.nurseassistant.data.model.Pasien
import kotlinx.android.synthetic.main.row_pasien.view.*

class TodoPasienRVAdapter constructor(val context: Context, val list: ArrayList<Pasien>)
    : RecyclerView.Adapter<TodoPasienRVAdapter.Item>() {

    lateinit var listener: OnItemClickListener

    fun setOnItemClick(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.row_pasien,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: List<Pasien>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Pasien>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: Pasien) {
            itemView.name.text = model.name
        }
    }
}