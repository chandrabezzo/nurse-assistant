package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Pasien
import kotlinx.android.synthetic.main.row_pasien.view.*



class TodoPasienRVAdapter constructor(val context: Context, val list: ArrayList<Pasien>)
    : RecyclerView.Adapter<TodoPasienRVAdapter.Item>() {

    interface OnItemClickListeners {
        fun onItemClick(item: Pasien)
    }

    private var listener: OnItemClickListeners? = null

    fun setOnItemClick(listener: OnItemClickListeners){
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
        holder.setContent(list[position])
    }

    fun setItem(values: List<Pasien>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Pasien>(itemView){

        override fun setContent(model: Pasien) {
            itemView.name.text = model.name
            itemView.floor.text = model.floor
            itemView.room.text = model.room
            itemView.bed.text = model.bed
            itemView.time.text = model.timeVisit
            itemView.container.removeAllViewsInLayout()
            for (value : String in model.todoList!!){
                val text = text()
                text.text = value
                itemView.container.addView(text)
            }
            itemView.layContent.setOnClickListener {
                listener?.onItemClick(model)
            }
        }

        private fun text() : TextView {
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