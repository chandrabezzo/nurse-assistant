package com.widyatama.nurseassistant.adapter.recycleview

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Pasien
import kotlinx.android.synthetic.main.item_rv_sample.view.*
import kotlinx.android.synthetic.main.row_pasien.view.*


/**
 * Created by iman on 29/01/2019.
 */

class PasienRVAdapter(var context : Context,
                      var list : ArrayList<Pasien>)
    : RecyclerView.Adapter<PasienRVAdapter.Item>() {

    lateinit var listener : OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_pasien, parent, false))
    }


    inner class Item(itemView : View) : BaseHolder<Pasien>(itemView){

        init {
            // add listener if you need
            itemView.setOnClickListener {
                listener.onItemClick(it, layoutPosition)
            }

            itemView.setOnLongClickListener {
                listener.onItemLongClick(it, layoutPosition)
            }
        }

        override fun setContent(model: Pasien) {
            itemView.name.text = model.name
            itemView.floor.text = model.floor
            itemView.room.text = model.room
            itemView.bed.text = model.bed
            itemView.time.text = model.timeVisit
            itemView.todo.text = model.todoList?.get(0)
            for (value : String in model.todoList!!){
                val text = text()
                text.text = value
                itemView.container.addView(text)
            }
        }

        private fun text() : TextView{
            val result = TextView(context)
            val param = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            result.layoutParams = param
            result.textSize = 14f
            result.setTextColor(context.resources.getColor(R.color.blueLight))
            return result
        }

    }

}