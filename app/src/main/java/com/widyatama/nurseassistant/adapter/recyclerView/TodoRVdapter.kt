package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import kotlinx.android.synthetic.main.row_pasien.view.todo


/**
 * Created by iman on 16/05/2019.
 */

class TodoRVdapter(var context : Context,
                      var list : ArrayList<String>)
    : RecyclerView.Adapter<TodoRVdapter.Item>() {

    lateinit var listener : OnItemClickListener

    fun setOnClick(listener: OnItemClickListener){
        this.listener = listener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.setContent(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_todo, parent, false))
    }

    fun setItem(values: List<String>){
        list.clear()
        list.addAll(values)

    }


    inner class Item(itemView : View) : BaseHolder<String>(itemView){

        init {
            // add listener if you need
            itemView.setOnClickListener {
                listener.onItemClick(it, layoutPosition)
            }

            itemView.setOnLongClickListener {
                listener.onItemLongClick(it, layoutPosition)
            }
        }

        override fun setContent(model: String) {
            itemView.todo.text = model
        }


    }

}